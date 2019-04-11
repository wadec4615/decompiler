package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Const;
import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.CPInstruction;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;
import com.oci.wade.decompiler.generic.PushInstruction;

public class LDC extends CPInstruction implements PushInstruction, ExceptionThrower {
  public LDC(int index) {
    super(Opcode.LDC_W, index);
    setSize();
  }

  @Override
  public Class<?>[] getExceptions() {
    return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_STRING_RESOLUTION);
  }

  @Override
  public void setIndex(int index) {
    super.setIndex(index);
    setSize();
  }

  protected void setSize() {
    if (super.getIndex() <= Const.MAX_BYTE) {
      super.setOpcode(Opcode.LDC);
      super.setLength(2);
    } else {
      super.setOpcode(Opcode.LDC_W);
      super.setLength(3);
    }
  }
}
