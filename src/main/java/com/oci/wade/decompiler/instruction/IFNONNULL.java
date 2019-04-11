package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IFNONNULL extends IfInstruction {
  public IFNONNULL(int offset) {
    super(Opcode.IFNONNULL, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IFNULL(offset);
  }
}
