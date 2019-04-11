package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.LocalVariableInstruction;

public class IINC extends LocalVariableInstruction {
  private boolean wide;
  private int increment;

  public IINC(int index, int increment) {
    super(Opcode.IINC);
    super.setLength(3);
    setIndex(index);
    setIncrement(increment);
    setWide(false);
  }

  public IINC(int index, int increment, boolean wide) {
    super(Opcode.IINC);
    super.setLength(3);
    setIndex(index);
    setIncrement(increment);
    setWide(wide);
    if (wide) {
      super.setLength(6);
    } else {
      super.setLength(3);
    }
  }

  public int getIncrement() {
    return increment;
  }

  public boolean isWide() {
    return wide;
  }

  public void setIncrement(int increment) {
    this.increment = increment;
  }

  @Override
  public void setWide(boolean wide) {
    this.wide = wide;
  }

  @Override
  public String toString() {
    return super.toString() + " " + increment;
  }
}
