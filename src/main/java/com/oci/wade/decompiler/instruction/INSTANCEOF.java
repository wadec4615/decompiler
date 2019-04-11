package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.CPInstruction;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;
import com.oci.wade.decompiler.generic.LoadClass;
import com.oci.wade.decompiler.generic.StackConsumer;
import com.oci.wade.decompiler.generic.StackProducer;

public class INSTANCEOF extends CPInstruction implements LoadClass, ExceptionThrower, StackProducer, StackConsumer {
  public INSTANCEOF(int index) {
    super(Opcode.INSTANCEOF, index);
  }

  @Override
  public Class<?>[] getExceptions() {
    return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION);
  }
}
