package com.oci.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.TypeConstants;
import com.oci.wade.decompiler.util.Utility;

public class LocalVariable implements Cloneable {
  private int start_pc;
  private int length;
  private int name_index;
  private int signature_index;
  private int index;
  private ConstantPool constant_pool;

  public LocalVariable(DataInput file, ConstantPool constant_pool) throws IOException {
    this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), constant_pool);
  }

  public LocalVariable(int start_pc, int length, int name_index, int signature_index, int index, ConstantPool constant_pool) {
    this.start_pc = start_pc;
    this.length = length;
    this.name_index = name_index;
    this.signature_index = signature_index;
    this.index = index;
    this.constant_pool = constant_pool;
  }

  public LocalVariable(LocalVariable c) {
    this(c.getStartPC(), c.getLength(), c.getNameIndex(), c.getSignatureIndex(), c.getIndex(), c.getConstantPool());
  }

  public LocalVariable copy() {
    try {
      return (LocalVariable) clone();
    } catch (CloneNotSupportedException e) {
    }
    return null;
  }

  public ConstantPool getConstantPool() {
    return constant_pool;
  }

  public int getIndex() {
    return index;
  }

  public int getLength() {
    return length;
  }

  public String getName() {
    ConstantUtf8 c = (ConstantUtf8) constant_pool.getConstant(name_index, TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public int getNameIndex() {
    return name_index;
  }

  public String getSignature() {
    ConstantUtf8 c = (ConstantUtf8) constant_pool.getConstant(signature_index, TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public int getSignatureIndex() {
    return signature_index;
  }

  public int getStartPC() {
    return start_pc;
  }

  public void setConstantPool(ConstantPool constant_pool) {
    this.constant_pool = constant_pool;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public void setNameIndex(int name_index) {
    this.name_index = name_index;
  }

  public void setSignatureIndex(int signature_index) {
    this.signature_index = signature_index;
  }

  public void setStartPC(int start_pc) {
    this.start_pc = start_pc;
  }

  @Override
  public String toString() {
    return toStringShared(false);
  }

  public String toStringShared(boolean typeTable) {
    String name = getName();
    String signature = Utility.signatureToString(getSignature(), false);
    String label = "LocalVariable" + (typeTable ? "Types" : "");
    return label + "(start_pc = " + start_pc + ", length = " + length + ", index = " + index + ":" + signature + " " + name + ")";
  }
}
