package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class FieldOrMethod extends CPInstruction implements LoadClass {
  public FieldOrMethod(Opcode opcode, int index) {
    super(opcode, index);
  }
}
