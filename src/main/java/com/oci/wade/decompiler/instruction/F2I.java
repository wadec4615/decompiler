package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class F2I extends ConversionInstruction {
  public F2I() {
    super(Opcode.F2I);
  }
}
