package com.oci.wade.decompiler.type;

import java.util.ArrayList;
import java.util.List;
import com.oci.wade.decompiler.constants.DataTypeConstants;
import com.oci.wade.decompiler.exception.ClassFormatException;
import com.oci.wade.decompiler.util.Utility;

public abstract class Type {
  public static BasicType VOID = new BasicType(DataTypeConstants.T_VOID);
  public static BasicType BOOLEAN = new BasicType(DataTypeConstants.T_BOOLEAN);
  public static BasicType INT = new BasicType(DataTypeConstants.T_INT);
  public static BasicType SHORT = new BasicType(DataTypeConstants.T_SHORT);
  public static BasicType BYTE = new BasicType(DataTypeConstants.T_BYTE);
  public static BasicType LONG = new BasicType(DataTypeConstants.T_LONG);
  public static BasicType DOUBLE = new BasicType(DataTypeConstants.T_DOUBLE);
  public static BasicType FLOAT = new BasicType(DataTypeConstants.T_FLOAT);
  public static BasicType CHAR = new BasicType(DataTypeConstants.T_CHAR);
  public static ObjectType OBJECT = new ObjectType("java.lang.Object");
  public static ObjectType CLASS = new ObjectType("java.lang.Class");
  public static ObjectType STRING = new ObjectType("java.lang.String");
  public static ObjectType STRINGBUFFER = new ObjectType("java.lang.StringBuffer");
  public static ObjectType THROWABLE = new ObjectType("java.lang.Throwable");
  public static Type[] NO_ARGS = new Type[0];
  public static ReferenceType NULL = new ReferenceType() {
  };
  public static Type UNKNOWN = new Type(DataTypeConstants.T_UNKNOWN, "<unknown object>") {
  };
  private static ThreadLocal<Integer> consumed_chars = new ThreadLocal<Integer>() {
    @Override
    protected Integer initialValue() {
      return Integer.valueOf(0);
    }
  };
  protected DataTypeConstants type;
  protected String signature;

  protected Type(DataTypeConstants t, String s) {
    type = t;
    signature = s;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Type) {
      Type t = (Type) o;
      return type == t.type && signature.equals(t.signature);
    }
    return false;
  }

  public String getSignature() {
    return signature;
  }

  public int getSize() {
    switch (type) {
      case T_DOUBLE:
      case T_LONG:
        return 2;
      case T_VOID:
        return 0;
      default:
        return 1;
    }
  }

  public DataTypeConstants getType() {
    return type;
  }

  public Type normalizeForStackOrLocal() {
    if (this == Type.BOOLEAN || this == Type.BYTE || this == Type.SHORT || this == Type.CHAR) {
      return Type.INT;
    }
    return this;
  }

  void setSignature(String signature) {
    this.signature = signature;
  }

  @Override
  public String toString() {
    return this.equals(Type.NULL) || type.getTag() >= DataTypeConstants.T_UNKNOWN.getTag() ? signature : Utility.signatureToString(signature, false);
  }

  static int consumed(int coded) {
    return coded >> 2;
  }

  static int encode(int size, int consumed) {
    return consumed << 2 | size;
  }

  public static Type[] getArgumentTypes(String signature) {
    List<Type> vec = new ArrayList<>();
    int index;
    Type[] types;
    try {
      if (signature.charAt(0) != '(') {
        throw new ClassFormatException("Invalid method signature: " + signature);
      }
      index = 1;
      while (signature.charAt(index) != ')') {
        vec.add(getType(signature.substring(index)));
        index += unwrap(consumed_chars);
      }
    } catch (StringIndexOutOfBoundsException e) {
      throw new ClassFormatException("Invalid method signature: " + signature, e);
    }
    types = new Type[vec.size()];
    vec.toArray(types);
    return types;
  }

  public static int getArgumentTypesSize(String signature) {
    int res = 0;
    int index;
    try {
      if (signature.charAt(0) != '(') {
        throw new ClassFormatException("Invalid method signature: " + signature);
      }
      index = 1;
      while (signature.charAt(index) != ')') {
        int coded = getTypeSize(signature.substring(index));
        res += size(coded);
        index += consumed(coded);
      }
    } catch (StringIndexOutOfBoundsException e) {
      throw new ClassFormatException("Invalid method signature: " + signature, e);
    }
    return res;
  }

  public static String getMethodSignature(Type return_type, Type[] arg_types) {
    StringBuilder buf = new StringBuilder("(");
    if (arg_types != null) {
      for (Type arg_type : arg_types) {
        buf.append(arg_type.getSignature());
      }
    }
    buf.append(')');
    buf.append(return_type.getSignature());
    return buf.toString();
  }

  public static Type getReturnType(String signature) {
    try {
      int index = signature.lastIndexOf(')') + 1;
      return getType(signature.substring(index));
    } catch (StringIndexOutOfBoundsException e) {
      throw new ClassFormatException("Invalid method signature: " + signature, e);
    }
  }

  public static int getReturnTypeSize(String signature) {
    int index = signature.lastIndexOf(')') + 1;
    return Type.size(getTypeSize(signature.substring(index)));
  }

  public static String getSignature(java.lang.reflect.Method meth) {
    StringBuilder sb = new StringBuilder("(");
    Class<?>[] params = meth.getParameterTypes();
    for (Class<?> param : params) {
      sb.append(getType(param).getSignature());
    }
    sb.append(")");
    sb.append(getType(meth.getReturnType()).getSignature());
    return sb.toString();
  }

  public static Type getType(java.lang.Class<?> cl) {
    if (cl == null) {
      throw new IllegalArgumentException("Class must not be null");
    }
    if (cl.isArray()) {
      return getType(cl.getName());
    } else if (cl.isPrimitive()) {
      if (cl == Integer.TYPE) {
        return INT;
      } else if (cl == Void.TYPE) {
        return VOID;
      } else if (cl == Double.TYPE) {
        return DOUBLE;
      } else if (cl == Float.TYPE) {
        return FLOAT;
      } else if (cl == Boolean.TYPE) {
        return BOOLEAN;
      } else if (cl == Byte.TYPE) {
        return BYTE;
      } else if (cl == Short.TYPE) {
        return SHORT;
      } else if (cl == Byte.TYPE) {
        return BYTE;
      } else if (cl == Long.TYPE) {
        return LONG;
      } else if (cl == Character.TYPE) {
        return CHAR;
      } else {
        throw new IllegalStateException("Ooops, what primitive type is " + cl);
      }
    } else {
      return ObjectType.getInstance(cl.getName());
    }
  }

  public static Type getType(String signature) throws StringIndexOutOfBoundsException {
    DataTypeConstants type = Utility.typeOfSignature(signature);
    if (type.getTag() <= DataTypeConstants.T_VOID.getTag()) {
      wrap(consumed_chars, 1);
      return BasicType.getType(type);
    } else if (type == DataTypeConstants.T_ARRAY) {
      int dim = 0;
      do {
        dim++;
      } while (signature.charAt(dim) == '[');
      Type t = getType(signature.substring(dim));
      int _temp = unwrap(consumed_chars) + dim;
      wrap(consumed_chars, _temp);
      return new ArrayType(t, dim);
    } else {
      String parsedSignature = Utility.signatureToString(signature, false);
      wrap(consumed_chars, parsedSignature.length() + 2);
      return ObjectType.getInstance(parsedSignature.replace('/', '.'));
    }
  }

  public static Type[] getTypes(java.lang.Class<?>[] classes) {
    Type[] ret = new Type[classes.length];
    for (int i = 0; i < ret.length; i++) {
      ret[i] = getType(classes[i]);
    }
    return ret;
  }

  public static int getTypeSize(String signature) throws StringIndexOutOfBoundsException {
    DataTypeConstants type = Utility.typeOfSignature(signature);
    if (type.getTag() <= DataTypeConstants.T_VOID.getTag()) {
      return encode(BasicType.getType(type).getSize(), 1);
    } else if (type == DataTypeConstants.T_ARRAY) {
      int dim = 0;
      do {
        dim++;
      } while (signature.charAt(dim) == '[');
      int consumed = consumed(getTypeSize(signature.substring(dim)));
      return encode(1, dim + consumed);
    } else {
      int index = signature.indexOf(';');
      if (index < 0) {
        throw new ClassFormatException("Invalid signature: " + signature);
      }
      return encode(1, index + 1);
    }
  }

  public static int size(int coded) {
    return coded & 3;
  }

  private static int unwrap(ThreadLocal<Integer> tl) {
    return tl.get().intValue();
  }

  private static void wrap(ThreadLocal<Integer> tl, int value) {
    tl.set(Integer.valueOf(value));
  }
}
