package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ReturnInstruction;

public class LRETURN extends ReturnInstruction {
  public LRETURN() {
    super(Opcode.LRETURN);
  }
}
