package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IF_ICMPEQ extends IfInstruction {
  public IF_ICMPEQ(int offset) {
    super(Opcode.IF_ICMPEQ, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IF_ICMPNE(offset);
  }
}
