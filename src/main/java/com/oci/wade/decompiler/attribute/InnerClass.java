package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;

public class InnerClass implements Cloneable {
  private int inner_class_index;
  private int outer_class_index;
  private int inner_name_index;
  private int inner_access_flags;

  public InnerClass(DataInput file) throws IOException {
    this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort());
  }

  public InnerClass(InnerClass c) {
    this(c.getInnerClassIndex(), c.getOuterClassIndex(), c.getInnerNameIndex(), c.getInnerAccessFlags());
  }

  public InnerClass(int inner_class_index, int outer_class_index, int inner_name_index, int inner_access_flags) {
    this.inner_class_index = inner_class_index;
    this.outer_class_index = outer_class_index;
    this.inner_name_index = inner_name_index;
    this.inner_access_flags = inner_access_flags;
  }

  public InnerClass copy() {
    try {
      return (InnerClass) clone();
    } catch (CloneNotSupportedException e) {
    }
    return null;
  }

  public int getInnerAccessFlags() {
    return inner_access_flags;
  }

  public int getInnerClassIndex() {
    return inner_class_index;
  }

  public int getInnerNameIndex() {
    return inner_name_index;
  }

  public int getOuterClassIndex() {
    return outer_class_index;
  }

  public void setInnerAccessFlags(int inner_access_flags) {
    this.inner_access_flags = inner_access_flags;
  }

  public void setInnerClassIndex(int inner_class_index) {
    this.inner_class_index = inner_class_index;
  }

  public void setInnerNameIndex(int inner_name_index) {
    this.inner_name_index = inner_name_index;
  }

  public void setOuterClassIndex(int outer_class_index) {
    this.outer_class_index = outer_class_index;
  }
}
