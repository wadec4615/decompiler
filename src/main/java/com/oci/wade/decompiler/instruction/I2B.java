package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class I2B extends ConversionInstruction {
  public I2B() {
    super(Opcode.I2B);
  }
}
