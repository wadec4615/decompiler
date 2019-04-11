package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IFGE extends IfInstruction {
  public IFGE(int offset) {
    super(Opcode.IFGE, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IFLT(offset);
  }
}
