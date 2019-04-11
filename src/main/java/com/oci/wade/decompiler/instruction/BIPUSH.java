package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ConstantPushInstruction;
import com.oci.wade.decompiler.generic.Instruction;

public class BIPUSH extends Instruction implements ConstantPushInstruction {
  private byte b;

  public BIPUSH(byte b) {
    super(Opcode.BIPUSH, 2);
    this.b = b;
  }

  @Override
  public Number getValue() {
    return Integer.valueOf(b);
  }
}
