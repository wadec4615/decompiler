package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.IfInstruction;

public class IFGT extends IfInstruction {
  public IFGT(int offset) {
    super(Opcode.IFGT, offset);
  }

  @Override
  public IfInstruction negate() {
    return new IFLE(offset);
  }
}
