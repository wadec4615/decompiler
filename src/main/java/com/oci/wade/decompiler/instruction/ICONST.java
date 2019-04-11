package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.exception.ClassGenException;
import com.oci.wade.decompiler.generic.ConstantPushInstruction;
import com.oci.wade.decompiler.generic.Instruction;

public class ICONST extends Instruction implements ConstantPushInstruction {
  private int value;

  public ICONST(int i) {
    super(Opcode.ICONST_0, 1);
    if ((i >= -1) && (i <= 5)) {
      super.setOpcode(Opcode.convert((short) (Opcode.ICONST_0.getValue() + i)));
    } else {
      throw new ClassGenException("ICONST can be used only for value between -1 and 5: " + i);
    }
    value = i;
  }

  @Override
  public Number getValue() {
    return Integer.valueOf(value);
  }
}
