package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ConstantNameAndType extends Constant {
  private int name_index;
  private int signature_index;

  public ConstantNameAndType(ConstantNameAndType c) {
    this(c.getNameIndex(), c.getSignatureIndex());
  }

  public ConstantNameAndType(DataInput file) throws IOException {
    this(file.readUnsignedShort(), file.readUnsignedShort());
  }

  public ConstantNameAndType(int name_index, int signature_index) {
    super(TypeConstants.CONSTANT_NameAndType);
    this.name_index = name_index;
    this.signature_index = signature_index;
  }

  public String getName(ConstantPool cp) {
    return cp.constantToString(getNameIndex(), TypeConstants.CONSTANT_Utf8);
  }

  public int getNameIndex() {
    return name_index;
  }

  public String getSignature(ConstantPool cp) {
    return cp.constantToString(getSignatureIndex(), TypeConstants.CONSTANT_Utf8);
  }

  public int getSignatureIndex() {
    return signature_index;
  }

  public void setNameIndex(int name_index) {
    this.name_index = name_index;
  }

  public void setSignatureIndex(int signature_index) {
    this.signature_index = signature_index;
  }

  @Override
  public String toString() {
    return super.toString() + "(name_index = " + name_index + ", signature_index = " + signature_index + ")";
  }
}
