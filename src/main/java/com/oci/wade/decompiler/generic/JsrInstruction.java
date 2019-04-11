package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class JsrInstruction extends BranchInstruction implements UnconditionalBranch, TypedInstruction, StackProducer {
  public JsrInstruction(Opcode opcode, int offset) {
    super(opcode, offset);
  }
}
