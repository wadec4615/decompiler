package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class I2C extends ConversionInstruction {
  public I2C() {
    super(Opcode.I2C);
  }
}
