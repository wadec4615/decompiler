package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.Instruction;

public class BREAKPOINT extends Instruction {
  public BREAKPOINT() {
    super(Opcode.BREAKPOINT, (short) 1);
  }
}
