package com.oci.wade.decompiler.classfile;

import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ElementValuePair {
  private ElementValue elementValue;
  private ConstantPool constantPool;
  private int elementNameIndex;

  public ElementValuePair(int elementNameIndex, ElementValue elementValue, ConstantPool constantPool) {
    this.elementValue = elementValue;
    this.elementNameIndex = elementNameIndex;
    this.constantPool = constantPool;
  }

  public int getNameIndex() {
    return elementNameIndex;
  }

  public String getNameString() {
    ConstantUtf8 c = (ConstantUtf8) constantPool.getConstant(elementNameIndex, TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public ElementValue getValue() {
    return elementValue;
  }
}
