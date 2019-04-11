package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.exception.ClassGenException;
import com.oci.wade.decompiler.generic.ConstantPushInstruction;
import com.oci.wade.decompiler.generic.Instruction;

public class LCONST extends Instruction implements ConstantPushInstruction {
  private long value;

  public LCONST(long value) {
    super(Opcode.LCONST_0, 1);
    if (value == 0) {
      super.setOpcode(Opcode.LCONST_0);
    } else if (value == 1) {
      super.setOpcode(Opcode.LCONST_1);
    } else {
      throw new ClassGenException("LCONST can be used only for 0 and 1: " + value);
    }
    this.value = value;
  }

  @Override
  public Number getValue() {
    return Long.valueOf(value);
  }
}
