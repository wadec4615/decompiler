package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class F2L extends ConversionInstruction {
  public F2L() {
    super(Opcode.F2L);
  }
}
