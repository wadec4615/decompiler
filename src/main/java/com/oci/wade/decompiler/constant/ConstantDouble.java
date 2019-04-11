package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ConstantDouble extends Constant implements ConstantObject {
  private double bytes;

  public ConstantDouble(ConstantDouble c) {
    this(c.getBytes());
  }

  public ConstantDouble(DataInput file) throws IOException {
    this(file.readDouble());
  }

  public ConstantDouble(double bytes) {
    super(TypeConstants.CONSTANT_Double);
    this.bytes = bytes;
  }

  public double getBytes() {
    return bytes;
  }

  @Override
  public Object getConstantValue(ConstantPool cp) {
    return new Double(bytes);
  }

  public void setBytes(double bytes) {
    this.bytes = bytes;
  }

  @Override
  public String toString() {
    return super.toString() + "(bytes = " + bytes + ")";
  }
}
