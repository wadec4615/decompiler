package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.CompoundInstruction;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.generic.StackInstruction;
import com.oci.wade.decompiler.generic.VariableLengthInstruction;

public class PUSH extends StackInstruction implements CompoundInstruction, VariableLengthInstruction {
  private Instruction instruction;

  public PUSH() {
    super(Opcode.PUSH);
  }

  public Instruction getInstruction() {
    return instruction;
  }
}
