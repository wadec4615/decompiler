package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ArithmeticInstruction;

public class FDIV extends ArithmeticInstruction {
  public FDIV() {
    super(Opcode.FDIV);
  }
}
