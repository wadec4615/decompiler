package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ConstantFloat extends Constant implements ConstantObject {
  private float bytes;

  public ConstantFloat(ConstantFloat c) {
    this(c.getBytes());
  }

  public ConstantFloat(DataInput file) throws IOException {
    this(file.readFloat());
  }

  public ConstantFloat(float bytes) {
    super(TypeConstants.CONSTANT_Float);
    this.bytes = bytes;
  }

  public float getBytes() {
    return bytes;
  }

  @Override
  public Object getConstantValue(ConstantPool cp) {
    return new Float(bytes);
  }

  public void setBytes(float bytes) {
    this.bytes = bytes;
  }

  @Override
  public String toString() {
    return super.toString() + "(bytes = " + bytes + ")";
  }
}
