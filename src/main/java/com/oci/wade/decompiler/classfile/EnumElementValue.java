package com.oci.wade.decompiler.classfile;

import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.TypeConstants;

public class EnumElementValue extends ElementValue {
  private int typeIdx;
  private int valueIdx;

  public EnumElementValue(int type, int typeIdx, int valueIdx, ConstantPool cpool) {
    super(type, cpool);
    if (type != ENUM_CONSTANT) {
      throw new RuntimeException("Only element values of type enum can be built with this ctor - type specified: " + type);
    }
    this.typeIdx = typeIdx;
    this.valueIdx = valueIdx;
  }

  public String getEnumTypeString() {
    ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(typeIdx, TypeConstants.CONSTANT_Utf8);
    return cu8.getBytes();
  }

  public String getEnumValueString() {
    ConstantUtf8 cu8 = (ConstantUtf8) super.getConstantPool().getConstant(valueIdx, TypeConstants.CONSTANT_Utf8);
    return cu8.getBytes();
  }

  public int getTypeIndex() {
    return typeIdx;
  }

  public int getValueIndex() {
    return valueIdx;
  }
}
