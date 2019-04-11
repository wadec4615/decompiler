package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.generic.UnconditionalBranch;

public class ATHROW extends Instruction implements UnconditionalBranch, ExceptionThrower {
  public ATHROW() {
    super(Opcode.ATHROW, (short) 1);
  }

  @Override
  public Class<?>[] getExceptions() {
    return new Class[] { ExceptionConst.THROWABLE };
  }
}
