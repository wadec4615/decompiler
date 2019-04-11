package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class I2L extends ConversionInstruction {
  public I2L() {
    super(Opcode.I2L);
  }
}
