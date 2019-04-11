package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class IfInstruction extends BranchInstruction implements StackConsumer {
  public IfInstruction(Opcode opcode, int offset) {
    super(opcode, offset);
  }

  public abstract IfInstruction negate();
}
