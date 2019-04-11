package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IFLT extends IfInstruction {
  public IFLT(int offset) {
    super(Opcode.IFLT, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IFGE(offset);
  }
}
