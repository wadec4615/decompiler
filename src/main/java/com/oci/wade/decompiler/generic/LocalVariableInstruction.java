package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class LocalVariableInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
  protected int index;
  private boolean wide;

  public LocalVariableInstruction(Opcode opcode) {
    super(opcode, 2);
    setWide(false);
  }

  public LocalVariableInstruction(Opcode opcode, int index) {
    super(opcode, 2);
    setWide(false);
    setIndex(index);
  }

  public LocalVariableInstruction(Opcode opcode, int index, boolean wide) {
    super(opcode, 2);
    setWide(wide);
    setIndex(index);
    if (wide) {
      super.setLength(4);
    } else {
      super.setLength(2);
    }
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public void setIndex(int index) {
    this.index = index;
  }

  public void setIndexOnly(int n) {
    this.index = n;
  }

  public void setWide(boolean wide) {
    this.wide = wide;
  }

  @Override
  public String toString() {
    return super.toString() + " " + index;
  }
}
