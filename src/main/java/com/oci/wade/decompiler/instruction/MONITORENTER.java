package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.generic.StackConsumer;

public class MONITORENTER extends Instruction implements ExceptionThrower, StackConsumer {
  public MONITORENTER() {
    super(Opcode.MONITORENTER, (short) 1);
  }

  @Override
  public Class<?>[] getExceptions() {
    return new Class[] { ExceptionConst.NULL_POINTER_EXCEPTION };
  }
}
