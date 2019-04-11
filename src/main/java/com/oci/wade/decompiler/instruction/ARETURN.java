package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ReturnInstruction;

public class ARETURN extends ReturnInstruction {
  public ARETURN() {
    super(Opcode.ARETURN);
  }
}
