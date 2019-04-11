package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.constants.TypeConstants;
import com.oci.wade.decompiler.exception.ClassFormatException;

public abstract class Constant implements Cloneable {
  protected TypeConstants tag;

  public Constant(TypeConstants tag) {
    this.tag = tag;
  }

  @Override
  public Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      throw new Error("Clone Not Supported");
    }
  }

  public Constant copy() {
    try {
      return (Constant) super.clone();
    } catch (CloneNotSupportedException e) {
    }
    return null;
  }

  public TypeConstants getTag() {
    return tag;
  }

  @Override
  public String toString() {
    return TypeConstants.getConstantName(tag) + "[" + tag + "]";
  }

  public static Constant readConstant(DataInput input) throws IOException, ClassFormatException {
    TypeConstants b = TypeConstants.getTag(input.readByte());
    switch (b) {
      case CONSTANT_Class:
        return new ConstantClass(input);
      case CONSTANT_Fieldref:
        return new ConstantFieldref(input);
      case CONSTANT_Methodref:
        return new ConstantMethodref(input);
      case CONSTANT_InterfaceMethodref:
        return new ConstantInterfaceMethodref(input);
      case CONSTANT_String:
        return new ConstantString(input);
      case CONSTANT_Integer:
        return new ConstantInteger(input);
      case CONSTANT_Float:
        return new ConstantFloat(input);
      case CONSTANT_Long:
        return new ConstantLong(input);
      case CONSTANT_Double:
        return new ConstantDouble(input);
      case CONSTANT_NameAndType:
        return new ConstantNameAndType(input);
      case CONSTANT_Utf8:
        return ConstantUtf8.getInstance(input);
      case CONSTANT_MethodHandle:
        return new ConstantMethodHandle(input);
      case CONSTANT_MethodType:
        return new ConstantMethodType(input);
      case CONSTANT_InvokeDynamic:
        return new ConstantInvokeDynamic(input);
      default:
        throw new ClassFormatException("Invalid byte tag in constant pool: " + b);
    }
  }
}
