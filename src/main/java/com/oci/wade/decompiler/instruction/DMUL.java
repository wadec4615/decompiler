package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ArithmeticInstruction;

public class DMUL extends ArithmeticInstruction {
  public DMUL() {
    super(Opcode.DMUL);
  }
}
