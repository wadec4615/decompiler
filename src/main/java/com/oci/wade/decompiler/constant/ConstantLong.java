package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ConstantLong extends Constant implements ConstantObject {
  private long bytes;

  public ConstantLong(ConstantLong c) {
    this(c.getBytes());
  }

  public ConstantLong(DataInput file) throws IOException {
    this(file.readLong());
  }

  public ConstantLong(long bytes) {
    super(TypeConstants.CONSTANT_Long);
    this.bytes = bytes;
  }

  public long getBytes() {
    return bytes;
  }

  @Override
  public Object getConstantValue(ConstantPool cp) {
    return Long.valueOf(bytes);
  }

  public void setBytes(long bytes) {
    this.bytes = bytes;
  }

  @Override
  public String toString() {
    return super.toString() + "(bytes = " + bytes + ")";
  }
}
