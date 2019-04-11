package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IFEQ extends IfInstruction {
  public IFEQ(int offset) {
    super(Opcode.IFEQ, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IFNE(offset);
  }
}
