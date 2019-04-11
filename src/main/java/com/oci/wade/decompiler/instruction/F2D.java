package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class F2D extends ConversionInstruction {
  public F2D() {
    super(Opcode.F2D);
  }
}
