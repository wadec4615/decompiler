package com.oci.wade.decompiler.classfile;

public class ArrayElementValue extends ElementValue {
  private ElementValue[] evalues;

  public ArrayElementValue(int type, ElementValue[] datums, ConstantPool cpool) {
    super(type, cpool);
    if (type != ARRAY) {
      throw new RuntimeException("Only element values of type array can be built with this ctor - type specified: " + type);
    }
    this.evalues = datums;
  }

  public ElementValue[] getElementValuesArray() {
    return evalues;
  }

  public int getElementValuesArraySize() {
    return evalues.length;
  }
}
