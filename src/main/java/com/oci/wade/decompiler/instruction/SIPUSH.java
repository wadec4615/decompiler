package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConstantPushInstruction;
import com.oci.wade.decompiler.generic.Instruction;

public class SIPUSH extends Instruction implements ConstantPushInstruction {
  private int b;

  public SIPUSH(int b) {
    super(Opcode.SIPUSH, 3);
    this.b = b;
  }

  @Override
  public Number getValue() {
    return Integer.valueOf(b);
  }
}
