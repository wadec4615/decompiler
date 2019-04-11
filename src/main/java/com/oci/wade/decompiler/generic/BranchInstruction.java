package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class BranchInstruction extends Instruction implements InstructionTargeter {
  protected int offset;

  public BranchInstruction(Opcode opcode, int offset) {
    super(opcode, 3);
    setOffset(offset);
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }
}
