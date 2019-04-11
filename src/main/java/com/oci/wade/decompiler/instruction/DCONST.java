package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.exception.ClassGenException;
import com.oci.wade.decompiler.generic.ConstantPushInstruction;
import com.oci.wade.decompiler.generic.Instruction;

public class DCONST extends Instruction implements ConstantPushInstruction {
  private double value;

  public DCONST(double f) {
    super(Opcode.DCONST_0, 1);
    if (f == 0.0) {
      super.setOpcode(Opcode.DCONST_0);
    } else if (f == 1.0) {
      super.setOpcode(Opcode.DCONST_1);
    } else {
      throw new ClassGenException("DCONST can be used only for 0.0 and 1.0: " + f);
    }
    value = f;
  }

  @Override
  public Number getValue() {
    return new Double(value);
  }
}
