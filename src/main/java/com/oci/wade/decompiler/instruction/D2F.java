package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class D2F extends ConversionInstruction {
  public D2F() {
    super(Opcode.D2F);
  }
}
