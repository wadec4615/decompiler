package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IF_ICMPLT extends IfInstruction {
  public IF_ICMPLT(int offset) {
    super(Opcode.IF_ICMPLT, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IF_ICMPGE(offset);
  }
}
