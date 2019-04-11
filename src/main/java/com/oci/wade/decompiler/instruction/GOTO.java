package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.GotoInstruction;
import com.oci.wade.decompiler.generic.VariableLengthInstruction;

public class GOTO extends GotoInstruction implements VariableLengthInstruction {
  public GOTO(short offset) {
    super(Opcode.GOTO, offset);
  }
}
