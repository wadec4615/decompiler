package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ConstantString extends Constant implements ConstantObject {
  private int string_index;

  public ConstantString(ConstantString c) {
    this(c.getStringIndex());
  }

  public ConstantString(DataInput file) throws IOException {
    this(file.readUnsignedShort());
  }

  public ConstantString(int string_index) {
    super(TypeConstants.CONSTANT_String);
    this.string_index = string_index;
  }

  public String getBytes(ConstantPool cp) {
    return (String) getConstantValue(cp);
  }

  @Override
  public Object getConstantValue(ConstantPool cp) {
    Constant c = cp.getConstant(string_index, TypeConstants.CONSTANT_Utf8);
    return ((ConstantUtf8) c).getBytes();
  }

  public int getStringIndex() {
    return string_index;
  }

  public void setStringIndex(int string_index) {
    this.string_index = string_index;
  }

  @Override
  public String toString() {
    return super.toString() + "(string_index = " + string_index + ")";
  }
}
