package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.exception.ClassGenException;
import com.oci.wade.decompiler.type.Type;

public abstract class ReturnInstruction extends Instruction implements ExceptionThrower, TypedInstruction, StackConsumer {
  public ReturnInstruction(Opcode opcode) {
    super(opcode, 1);
  }

  @Override
  public Class<?>[] getExceptions() {
    return new Class[] { ExceptionConst.ILLEGAL_MONITOR_STATE };
  }

  public Type getType() {
    Opcode opcode = super.getOpcode();
    switch (opcode) {
      case IRETURN:
        return Type.INT;
      case LRETURN:
        return Type.LONG;
      case FRETURN:
        return Type.FLOAT;
      case DRETURN:
        return Type.DOUBLE;
      case ARETURN:
        return Type.OBJECT;
      case RETURN:
        return Type.VOID;
      default:
        throw new ClassGenException("Unknown type " + opcode);
    }
  }
}
