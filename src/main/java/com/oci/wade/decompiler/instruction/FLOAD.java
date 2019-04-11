package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.LoadInstruction;

public class FLOAD extends LoadInstruction {
  public FLOAD(Opcode opcode) {
    super(opcode);
  }

  public FLOAD(Opcode opcode, int index) {
    super(opcode, index, false);
  }

  public FLOAD(Opcode opcode, int index, boolean wide) {
    super(opcode, index, wide);
  }
}
