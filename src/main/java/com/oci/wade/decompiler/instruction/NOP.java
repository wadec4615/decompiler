package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.Instruction;

public class NOP extends Instruction {
  public NOP() {
    super(Opcode.NOP, (short) 1);
  }
}
