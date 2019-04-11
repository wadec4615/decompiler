package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.exception.ClassGenException;
import com.oci.wade.decompiler.generic.ConstantPushInstruction;
import com.oci.wade.decompiler.generic.Instruction;

public class FCONST extends Instruction implements ConstantPushInstruction {
  private float value;

  public FCONST(float f) {
    super(Opcode.FCONST_0, 1);
    if (f == 0.0) {
      super.setOpcode(Opcode.FCONST_0);
    } else if (f == 1.0) {
      super.setOpcode(Opcode.FCONST_1);
    } else if (f == 2.0) {
      super.setOpcode(Opcode.FCONST_2);
    } else {
      throw new ClassGenException("FCONST can be used only for 0.0, 1.0 and 2.0: " + f);
    }
    value = f;
  }

  @Override
  public Number getValue() {
    return new Float(value);
  }
}
