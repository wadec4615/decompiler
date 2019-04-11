package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.PopInstruction;
import com.oci.wade.decompiler.generic.StackInstruction;

public class POP extends StackInstruction implements PopInstruction {
  public POP() {
    super(Opcode.POP);
  }
}
