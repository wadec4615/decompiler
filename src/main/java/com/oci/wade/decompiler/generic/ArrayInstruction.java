package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class ArrayInstruction extends Instruction implements ExceptionThrower, TypedInstruction {
  public ArrayInstruction(Opcode opcode) {
    super(opcode, 1);
  }

  @Override
  public Class<?>[] getExceptions() {
    return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
  }
}
