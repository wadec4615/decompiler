package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.generic.StackConsumer;
import com.oci.wade.decompiler.generic.StackProducer;

public class ARRAYLENGTH extends Instruction implements ExceptionThrower, StackProducer, StackConsumer {
  public ARRAYLENGTH() {
    super(Opcode.ARRAYLENGTH, (short) 1);
  }

  @Override
  public Class<?>[] getExceptions() {
    return new Class[] { ExceptionConst.NULL_POINTER_EXCEPTION };
  }
}
