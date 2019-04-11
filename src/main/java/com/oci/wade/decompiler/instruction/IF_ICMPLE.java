package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IF_ICMPLE extends IfInstruction {
  public IF_ICMPLE(int offset) {
    super(Opcode.IF_ICMPLE, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IF_ICMPGT(offset);
  }
}
