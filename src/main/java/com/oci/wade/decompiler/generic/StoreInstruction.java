package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class StoreInstruction extends LocalVariableInstruction implements PopInstruction {
  public StoreInstruction(Opcode opcode) {
    super(opcode);
  }

  protected StoreInstruction(Opcode opcode, int index, boolean wide) {
    super(opcode, index, wide);
  }
}
