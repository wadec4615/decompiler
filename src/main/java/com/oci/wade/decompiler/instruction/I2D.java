package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class I2D extends ConversionInstruction {
  public I2D() {
    super(Opcode.I2D);
  }
}
