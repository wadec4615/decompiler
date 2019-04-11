package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ArithmeticInstruction;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;

public class IREM extends ArithmeticInstruction implements ExceptionThrower {
  public IREM() {
    super(Opcode.IREM);
  }

  @Override
  public Class<?>[] getExceptions() {
    return new Class[] { ExceptionConst.ARITHMETIC_EXCEPTION };
  }
}
