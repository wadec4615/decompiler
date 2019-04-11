package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ReturnInstruction;

public class DRETURN extends ReturnInstruction {
  public DRETURN() {
    super(Opcode.DRETURN);
  }
}
