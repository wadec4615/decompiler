package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IF_ACMPNE extends IfInstruction {
  public IF_ACMPNE(int offset) {
    super(Opcode.IF_ACMPNE, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IF_ACMPEQ(offset);
  }
}
