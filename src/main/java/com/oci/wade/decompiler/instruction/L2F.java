package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class L2F extends ConversionInstruction {
  public L2F() {
    super(Opcode.L2F);
  }
}
