package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;
import com.oci.wade.decompiler.generic.FieldInstruction;
import com.oci.wade.decompiler.generic.PushInstruction;

public class GETSTATIC extends FieldInstruction implements PushInstruction, ExceptionThrower {
  public GETSTATIC(int index) {
    super(Opcode.GETSTATIC, index);
  }

  @Override
  public Class<?>[] getExceptions() {
    return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
  }
}
