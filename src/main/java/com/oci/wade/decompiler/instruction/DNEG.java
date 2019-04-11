package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ArithmeticInstruction;

public class DNEG extends ArithmeticInstruction {
  public DNEG() {
    super(Opcode.DNEG);
  }
}
