package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class LoadInstruction extends LocalVariableInstruction implements PushInstruction {
  public LoadInstruction(Opcode opcode) {
    super(opcode);
  }

  protected LoadInstruction(Opcode opcode, int index, boolean wide) {
    super(opcode, index, wide);
  }
}
