package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.GotoInstruction;

public class GOTO_W extends GotoInstruction {
  public GOTO_W(int offset) {
    super(Opcode.GOTO_W, offset);
    super.setLength(5);
  }
}
