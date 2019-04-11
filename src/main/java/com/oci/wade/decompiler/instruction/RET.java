package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.exception.ClassGenException;
import com.oci.wade.decompiler.generic.IndexedInstruction;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.generic.TypedInstruction;

public class RET extends Instruction implements IndexedInstruction, TypedInstruction {
  private boolean wide;
  private int index;

  public RET(int index) {
    super(Opcode.RET, 2);
    setIndex(index);
    setWide(false);
  }

  public RET(int index, boolean wide) {
    super(Opcode.RET, 2);
    setIndex(index);
    setWide(wide);
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public void setIndex(int index) {
    if (index < 0) {
      throw new ClassGenException("Negative index value: " + index);
    }
    this.index = index;
  }

  public void setWide(boolean wide) {
    this.wide = wide;
  }

  @Override
  public String toString() {
    return super.toString() + " " + index;
  }
}
