package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IF_ICMPNE extends IfInstruction {
  public IF_ICMPNE(int offset) {
    super(Opcode.IF_ICMPNE, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IF_ICMPEQ(offset);
  }
}
