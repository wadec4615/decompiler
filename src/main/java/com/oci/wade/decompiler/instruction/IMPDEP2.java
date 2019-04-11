package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.Instruction;

public class IMPDEP2 extends Instruction {
  public IMPDEP2() {
    super(Opcode.IMPDEP2, (short) 1);
  }
}
