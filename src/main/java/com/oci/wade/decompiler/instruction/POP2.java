package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.PopInstruction;
import com.oci.wade.decompiler.generic.StackInstruction;

public class POP2 extends StackInstruction implements PopInstruction {
  public POP2() {
    super(Opcode.POP2);
  }
}
