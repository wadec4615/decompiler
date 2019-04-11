package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IF_ICMPGE extends IfInstruction {
  public IF_ICMPGE(int offset) {
    super(Opcode.IF_ICMPGE, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IF_ICMPLT(offset);
  }
}
