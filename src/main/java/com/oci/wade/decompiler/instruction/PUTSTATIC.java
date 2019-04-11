package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;
import com.oci.wade.decompiler.generic.FieldInstruction;
import com.oci.wade.decompiler.generic.PopInstruction;

public class PUTSTATIC extends FieldInstruction implements ExceptionThrower, PopInstruction {
  public PUTSTATIC(int index) {
    super(Opcode.PUTSTATIC, index);
  }

  @Override
  public Class<?>[] getExceptions() {
    return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
  }
}
