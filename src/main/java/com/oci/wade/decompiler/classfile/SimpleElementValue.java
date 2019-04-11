package com.oci.wade.decompiler.classfile;

import com.oci.wade.decompiler.constant.ConstantDouble;
import com.oci.wade.decompiler.constant.ConstantFloat;
import com.oci.wade.decompiler.constant.ConstantInteger;
import com.oci.wade.decompiler.constant.ConstantLong;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.TypeConstants;

public class SimpleElementValue extends ElementValue {
  private int index;

  public SimpleElementValue(int type, int index, ConstantPool cpool) {
    super(type, cpool);
    this.index = index;
  }

  public int getIndex() {
    return index;
  }

  public boolean getValueBoolean() {
    if (super.getType() != PRIMITIVE_BOOLEAN) {
      throw new RuntimeException("Dont call getValueBoolean() on a non BOOLEAN ElementValue");
    }
    ConstantInteger bo = (ConstantInteger) super.getConstantPool().getConstant(getIndex());
    return bo.getBytes() != 0;
  }

  public byte getValueByte() {
    if (super.getType() != PRIMITIVE_BYTE) {
      throw new RuntimeException("Dont call getValueByte() on a non BYTE ElementValue");
    }
    ConstantInteger c = (ConstantInteger) super.getConstantPool().getConstant(getIndex(), TypeConstants.CONSTANT_Integer);
    return (byte) c.getBytes();
  }

  public char getValueChar() {
    if (super.getType() != PRIMITIVE_CHAR) {
      throw new RuntimeException("Dont call getValueChar() on a non CHAR ElementValue");
    }
    ConstantInteger c = (ConstantInteger) super.getConstantPool().getConstant(getIndex(), TypeConstants.CONSTANT_Integer);
    return (char) c.getBytes();
  }

  public double getValueDouble() {
    if (super.getType() != PRIMITIVE_DOUBLE) {
      throw new RuntimeException("Dont call getValueDouble() on a non DOUBLE ElementValue");
    }
    ConstantDouble d = (ConstantDouble) super.getConstantPool().getConstant(getIndex());
    return d.getBytes();
  }

  public float getValueFloat() {
    if (super.getType() != PRIMITIVE_FLOAT) {
      throw new RuntimeException("Dont call getValueFloat() on a non FLOAT ElementValue");
    }
    ConstantFloat f = (ConstantFloat) super.getConstantPool().getConstant(getIndex());
    return f.getBytes();
  }

  public int getValueInt() {
    if (super.getType() != PRIMITIVE_INT) {
      throw new RuntimeException("Dont call getValueString() on a non STRING ElementValue");
    }
    ConstantInteger c = (ConstantInteger) super.getConstantPool().getConstant(getIndex(), TypeConstants.CONSTANT_Integer);
    return c.getBytes();
  }

  public long getValueLong() {
    if (super.getType() != PRIMITIVE_LONG) {
      throw new RuntimeException("Dont call getValueLong() on a non LONG ElementValue");
    }
    ConstantLong j = (ConstantLong) super.getConstantPool().getConstant(getIndex());
    return j.getBytes();
  }

  public short getValueShort() {
    if (super.getType() != PRIMITIVE_SHORT) {
      throw new RuntimeException("Dont call getValueShort() on a non SHORT ElementValue");
    }
    ConstantInteger s = (ConstantInteger) super.getConstantPool().getConstant(getIndex());
    return (short) s.getBytes();
  }

  public String getValueString() {
    if (super.getType() != STRING) {
      throw new RuntimeException("Dont call getValueString() on a non STRING ElementValue");
    }
    ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(getIndex(), TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public void setIndex(int index) {
    this.index = index;
  }
}
