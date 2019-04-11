package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;
import com.oci.wade.decompiler.generic.FieldInstruction;
import com.oci.wade.decompiler.generic.PopInstruction;

public class PUTFIELD extends FieldInstruction implements PopInstruction, ExceptionThrower {
  public PUTFIELD(int index) {
    super(Opcode.PUTFIELD, index);
  }

  @Override
  public Class<?>[] getExceptions() {
    return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
  }
}
