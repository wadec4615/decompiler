package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IF_ACMPEQ extends IfInstruction {
  public IF_ACMPEQ(int offset) {
    super(Opcode.IF_ACMPEQ, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IF_ACMPNE(offset);
  }
}
