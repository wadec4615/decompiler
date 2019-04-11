package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IFNULL extends IfInstruction {
  public IFNULL(int offset) {
    super(Opcode.IFNULL, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IFNONNULL(offset);
  }
}
