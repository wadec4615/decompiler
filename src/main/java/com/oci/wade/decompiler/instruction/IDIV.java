package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ArithmeticInstruction;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;

public class IDIV extends ArithmeticInstruction implements ExceptionThrower {
  public IDIV() {
    super(Opcode.IDIV);
  }

  @Override
  public Class<?>[] getExceptions() {
    return new Class[] { ExceptionConst.ARITHMETIC_EXCEPTION };
  }
}
