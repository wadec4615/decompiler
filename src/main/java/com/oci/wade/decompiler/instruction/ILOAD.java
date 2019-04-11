package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.LoadInstruction;

public class ILOAD extends LoadInstruction {
  public ILOAD(Opcode opcode) {
    super(opcode);
  }

  public ILOAD(Opcode opcode, int index) {
    super(opcode, index, false);
  }

  public ILOAD(Opcode opcode, int index, boolean wide) {
    super(opcode, index, wide);
  }
}
