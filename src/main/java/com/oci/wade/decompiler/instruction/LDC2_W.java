package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.CPInstruction;
import com.oci.wade.decompiler.generic.PushInstruction;

public class LDC2_W extends CPInstruction implements PushInstruction {
  public LDC2_W(int index) {
    super(Opcode.LDC2_W, index);
  }
}
