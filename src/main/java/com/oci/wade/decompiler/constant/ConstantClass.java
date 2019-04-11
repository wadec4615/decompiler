package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ConstantClass extends Constant implements ConstantObject {
  private int name_index;

  public ConstantClass(ConstantClass c) {
    this(c.getNameIndex());
  }

  public ConstantClass(DataInput file) throws IOException {
    this(file.readUnsignedShort());
  }

  public ConstantClass(int name_index) {
    super(TypeConstants.CONSTANT_Class);
    this.name_index = name_index;
  }

  public String getBytes(ConstantPool cp) {
    return (String) getConstantValue(cp);
  }

  @Override
  public Object getConstantValue(ConstantPool cp) {
    Constant c = cp.getConstant(name_index, TypeConstants.CONSTANT_Utf8);
    return ((ConstantUtf8) c).getBytes();
  }

  public int getNameIndex() {
    return name_index;
  }

  public void setNameIndex(int name_index) {
    this.name_index = name_index;
  }

  @Override
  public String toString() {
    return super.toString() + "(name_index = " + name_index + ")";
  }
}
