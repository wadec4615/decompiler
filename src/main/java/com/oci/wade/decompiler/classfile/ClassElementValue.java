package com.oci.wade.decompiler.classfile;

import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ClassElementValue extends ElementValue {
  private int idx;

  public ClassElementValue(int type, int idx, ConstantPool cpool) {
    super(type, cpool);
    this.idx = idx;
  }

  public String getClassString() {
    ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(idx, TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public int getIndex() {
    return idx;
  }
}
