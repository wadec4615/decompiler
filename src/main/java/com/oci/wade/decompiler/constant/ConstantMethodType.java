package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ConstantMethodType extends Constant {
  private int descriptor_index;

  public ConstantMethodType(ConstantMethodType c) {
    this(c.getDescriptorIndex());
  }

  public ConstantMethodType(DataInput file) throws IOException {
    this(file.readUnsignedShort());
  }

  public ConstantMethodType(int descriptor_index) {
    super(TypeConstants.CONSTANT_MethodType);
    this.descriptor_index = descriptor_index;
  }

  public int getDescriptorIndex() {
    return descriptor_index;
  }

  public void setDescriptorIndex(int descriptor_index) {
    this.descriptor_index = descriptor_index;
  }

  @Override
  public String toString() {
    return super.toString() + "(descriptor_index = " + descriptor_index + ")";
  }
}
