package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.LoadInstruction;

public class DLOAD extends LoadInstruction {
  public DLOAD(Opcode opcode) {
    super(opcode);
  }

  public DLOAD(Opcode opcode, int index) {
    super(opcode, index, false);
  }

  public DLOAD(Opcode opcode, int index, boolean wide) {
    super(opcode, index, wide);
  }
}
