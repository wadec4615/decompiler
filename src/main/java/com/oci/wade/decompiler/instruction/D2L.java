package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class D2L extends ConversionInstruction {
  public D2L() {
    super(Opcode.D2L);
  }
}
