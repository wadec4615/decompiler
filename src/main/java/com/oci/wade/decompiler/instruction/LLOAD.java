package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.LoadInstruction;

public class LLOAD extends LoadInstruction {
  public LLOAD(Opcode opcode) {
    super(opcode);
  }

  public LLOAD(Opcode opcode, int index) {
    super(opcode, index, false);
  }

  public LLOAD(Opcode opcode, int index, boolean wide) {
    super(opcode, index, wide);
  }
}
