package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class NameSignatureInstruction extends CPInstruction {
  public NameSignatureInstruction(Opcode opcode, int index) {
    super(opcode, index);
  }
}
