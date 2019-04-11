package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IFNE extends IfInstruction {
  public IFNE(int offset) {
    super(Opcode.IFNE, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IFEQ(offset);
  }
}
