package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;
import com.oci.wade.decompiler.generic.FieldInstruction;
import com.oci.wade.decompiler.generic.StackConsumer;
import com.oci.wade.decompiler.generic.StackProducer;

public class GETFIELD extends FieldInstruction implements ExceptionThrower, StackConsumer, StackProducer {
  public GETFIELD(int index) {
    super(Opcode.GETFIELD, index);
  }

  @Override
  public Class<?>[] getExceptions() {
    return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
  }
}
