package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IF_ICMPGT extends IfInstruction {
  public IF_ICMPGT(int offset) {
    super(Opcode.IF_ICMPGT, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IF_ICMPLE(offset);
  }
}
