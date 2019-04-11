package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class GotoInstruction extends BranchInstruction implements UnconditionalBranch {
  public GotoInstruction(Opcode opcode, int offset) {
    super(opcode, offset);
  }
}
