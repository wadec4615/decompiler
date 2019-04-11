package com.oci.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.AccessConstants;
import com.oci.wade.decompiler.constants.TypeConstants;

public class MethodParameter implements Cloneable {
  private int name_index;
  private int access_flags;

  public MethodParameter() {
  }

  public MethodParameter(DataInput input) throws IOException {
    name_index = input.readUnsignedShort();
    access_flags = input.readUnsignedShort();
  }

  public MethodParameter copy() {
    try {
      return (MethodParameter) clone();
    } catch (CloneNotSupportedException e) {
    }
    return null;
  }

  public int getAccessFlags() {
    return access_flags;
  }

  public int getNameIndex() {
    return name_index;
  }

  public String getParameterName(ConstantPool constant_pool) {
    if (name_index == 0) {
      return null;
    }
    return ((ConstantUtf8) constant_pool.getConstant(name_index, TypeConstants.CONSTANT_Utf8)).getBytes();
  }

  public boolean is() {
    return (access_flags & AccessConstants.ACC_.getValue()) != 0;
  }

  public boolean isMandated() {
    return (access_flags & AccessConstants.ACC_MANDATED.getValue()) != 0;
  }

  public boolean isSynthetic() {
    return (access_flags & AccessConstants.ACC_SYNTHETIC.getValue()) != 0;
  }

  public void setAccessFlags(int access_flags) {
    this.access_flags = access_flags;
  }

  public void setNameIndex(int name_index) {
    this.name_index = name_index;
  }
}
