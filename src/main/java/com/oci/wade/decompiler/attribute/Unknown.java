package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.AttributeConstants;
import com.oci.wade.decompiler.constants.TypeConstants;

public class Unknown extends Attribute {
  private static Map<String, Unknown> unknown_attributes = new HashMap<>();
  private byte[] bytes;
  private String name;

  public Unknown(int name_index, int length, byte[] bytes, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_UNKNOWN, name_index, length, constant_pool);
    this.bytes = bytes;
    name = ((ConstantUtf8) constant_pool.getConstant(name_index, TypeConstants.CONSTANT_Utf8)).getBytes();
    unknown_attributes.put(name, this);
  }

  public Unknown(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, (byte[]) null, constant_pool);
    if (length > 0) {
      bytes = new byte[length];
      input.readFully(bytes);
    }
  }

  public Unknown(Unknown c) {
    this(c.getNameIndex(), c.getLength(), c.getBytes(), c.getConstantPool());
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public byte[] getBytes() {
    return bytes;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

  static Unknown[] getUnknownAttributes() {
    Unknown[] unknowns = new Unknown[unknown_attributes.size()];
    unknown_attributes.values().toArray(unknowns);
    unknown_attributes.clear();
    return unknowns;
  }
}
