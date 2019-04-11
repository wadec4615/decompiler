package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ArithmeticInstruction;

public class LMUL extends ArithmeticInstruction {
  public LMUL() {
    super(Opcode.LMUL);
  }
}
