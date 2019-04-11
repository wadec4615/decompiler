package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class StackInstruction extends Instruction {
  public StackInstruction(Opcode opcode) {
    super(opcode, 1);
  }
}
