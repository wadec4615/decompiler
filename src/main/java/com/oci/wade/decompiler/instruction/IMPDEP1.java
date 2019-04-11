package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.Instruction;

public class IMPDEP1 extends Instruction {
  public IMPDEP1() {
    super(Opcode.IMPDEP1, (short) 1);
  }
}
