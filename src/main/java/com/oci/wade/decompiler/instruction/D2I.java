package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class D2I extends ConversionInstruction {
  public D2I() {
    super(Opcode.D2I);
  }
}
