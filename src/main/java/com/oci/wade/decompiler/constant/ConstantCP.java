package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.TypeConstants;

public abstract class ConstantCP extends Constant {
  protected int class_index;
  protected int name_and_type_index;

  public ConstantCP(ConstantCP c) {
    this(c.getTag(), c.getClassIndex(), c.getNameAndTypeIndex());
  }

  public ConstantCP(TypeConstants tag, DataInput file) throws IOException {
    this(tag, file.readUnsignedShort(), file.readUnsignedShort());
  }

  public ConstantCP(TypeConstants tag, int class_index, int name_and_type_index) {
    super(tag);
    this.class_index = class_index;
    this.name_and_type_index = name_and_type_index;
  }

  public String getClass(ConstantPool cp) {
    return cp.constantToString(class_index, TypeConstants.CONSTANT_Class);
  }

  public int getClassIndex() {
    return class_index;
  }

  public int getNameAndTypeIndex() {
    return name_and_type_index;
  }

  public void setClassIndex(int class_index) {
    this.class_index = class_index;
  }

  public void setNameAndTypeIndex(int name_and_type_index) {
    this.name_and_type_index = name_and_type_index;
  }

  @Override
  public String toString() {
    return super.toString() + "(class_index = " + class_index + ", name_and_type_index = " + name_and_type_index + ")";
  }
}
