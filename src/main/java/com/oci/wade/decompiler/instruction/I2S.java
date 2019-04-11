package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class I2S extends ConversionInstruction {
  public I2S() {
    super(Opcode.I2S);
  }
}
