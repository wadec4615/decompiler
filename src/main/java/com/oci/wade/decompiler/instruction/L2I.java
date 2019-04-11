package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class L2I extends ConversionInstruction {
  public L2I() {
    super(Opcode.L2I);
  }
}
