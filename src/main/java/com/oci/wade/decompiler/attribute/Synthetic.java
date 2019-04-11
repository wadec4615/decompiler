package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.AttributeConstants;

public class Synthetic extends Attribute {
  private byte[] bytes;

  public Synthetic(int name_index, int length, byte[] bytes, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_SYNTHETIC, name_index, length, constant_pool);
    this.bytes = bytes;
  }

  public Synthetic(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, (byte[]) null, constant_pool);
    if (length > 0) {
      bytes = new byte[length];
      input.readFully(bytes);
      System.err.println("Synthetic attribute with length > 0");
    }
  }

  public Synthetic(Synthetic c) {
    this(c.getNameIndex(), c.getLength(), c.getBytes(), c.getConstantPool());
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public byte[] getBytes() {
    return bytes;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }
}
