package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConversionInstruction;

public class I2F extends ConversionInstruction {
  public I2F() {
    super(Opcode.I2F);
  }
}
