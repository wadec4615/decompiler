package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IFLE extends IfInstruction {
  public IFLE(int offset) {
    super(Opcode.IFLE, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IFGT(offset);
  }
}
