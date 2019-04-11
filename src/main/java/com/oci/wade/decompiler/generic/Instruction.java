package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.Opcode;

public abstract class Instruction implements Cloneable {
  protected int length = 1;
  protected Opcode opcode = Opcode.UNDEFINED;

  public Instruction(Opcode opcode, int length) {
    this.length = length;
    this.opcode = opcode;
  }

  public int getLength() {
    return length;
  }

  public String getName() {
    return Opcode.getOpcodeName(opcode);
  }

  public Opcode getOpcode() {
    return opcode;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public void setOpcode(Opcode opcode) {
    this.opcode = opcode;
  }

  @Override
  public String toString() {
    return getName() + "[" + opcode + "](" + length + ")";
  }

  public String toString(ConstantPool cp) {
    return toString();
  }
}
