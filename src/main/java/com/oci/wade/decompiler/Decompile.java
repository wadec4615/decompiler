package com.oci.wade.decompiler;

import java.io.PrintStream;
import com.oci.wade.decompiler.attribute.Attribute;
import com.oci.wade.decompiler.classfile.Field;
import com.oci.wade.decompiler.classfile.JavaClass;
import com.oci.wade.decompiler.classfile.Method;
import com.oci.wade.decompiler.util.Utility;

public class Decompile {
  private String access;
  private String type;
  private String className;
  private String superClassName;
  private String[] interfaces;
  private Attribute[] attributes;
  private Field[] fields;
  private Method[] methods;
  private String packageName;
  private boolean isEnum;
  private boolean isPublic;
  private boolean isProtected;
  private boolean isPrivate;
  private boolean isClass;
  private boolean isInterface;

  public Decompile(JavaClass clazz) {
    isPublic = clazz.isPublic();
    isEnum = clazz.isEnum();
    isProtected = clazz.isProtected();
    isPrivate = clazz.isPrivate();
    isClass = clazz.isClass();
    isInterface = clazz.isInterface();
    access = Utility.accessToString(clazz.getAccessFlags(), true);
    access = access.isEmpty() ? "" : access + " ";
    type = Utility.classOrInterface(clazz.getAccessFlags());
    packageName = clazz.getPackageName();
    className = Utility.getConstructorName(clazz.getClassName(), '.');
    superClassName = Utility.compactClassName(clazz.getSuperclassName(), false);
    interfaces = clazz.getInterfaceNames();
    attributes = clazz.getAttributes();
    fields = clazz.getFields();
    methods = clazz.getMethods();
  }

  public void decompile(PrintStream out, String indent) throws Exception {
    out.println(indent + "package " + packageName + ";\n");
    if (isEnum) {
      decompileEnum(out, indent);
    } else {
      decompileClassOrInterface(out, indent);
    }
  }

  private void decompileClassOrInterface(PrintStream out, String indent) throws Exception {
    out.print(indent);
    getAccess(out, indent);
    out.print(" ");
    getType(out, indent);
    out.print(" ");
    out.print(className);
    out.print(" extends ");
    out.print(superClassName);
    int size = interfaces.length;
    if (size > 0) {
      out.print(" implements ");
      for (int i = 0; i < size; i++) {
        out.print(interfaces[i]);
        if (i < (size - 1)) {
          out.print(", ");
        }
      }
    }
    out.print(" {\n");
    if (attributes.length > 0) {
      for (Attribute attribute : attributes) {
        attribute.decompile(out, indent + " ");
      }
    }
    if (fields.length > 0) {
      for (Field field : fields) {
        field.decompile(out, indent + " ");
      }
    }
    if (methods.length > 0) {
      out.print("\n");
      for (Method method : methods) {
        method.decompile(out, indent + " ");
      }
    }
    out.print(indent + "}\n");
  }

  private void decompileEnum(PrintStream out, String indent) throws Exception {
    out.print(indent);
    getAccess(out, indent);
    out.print(" ");
    getType(out, indent);
    out.print(" ");
    out.print(className);
    out.print(" {\n");
    if (fields.length > 0) {
      for (Field field : fields) {
        if (!field.isSynthetic()) {
          field.decompile(out, indent + " ");
        }
      }
    }
    if (methods.length > 0) {
      out.print("\n");
      for (Method method : methods) {
        method.decompile(out, indent + " ");
      }
    }
    out.print(indent + "}\n");
  }

  private void getAccess(PrintStream out, String indent) {
    if (isPublic) {
      out.print("public");
    } else if (isPrivate) {
      out.print("private");
    } else if (isProtected) {
      out.print("protected");
    }
  }

  private void getType(PrintStream out, String indent) {
    if (isEnum) {
      out.print("enum");
    } else if (isClass) {
      out.print("class");
    } else if (isInterface) {
      out.print("interface");
    }
  }
}
