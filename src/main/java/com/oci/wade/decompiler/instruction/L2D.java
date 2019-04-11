package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class L2D extends ConversionInstruction {
  public L2D() {
    super(Opcode.L2D);
  }
}
