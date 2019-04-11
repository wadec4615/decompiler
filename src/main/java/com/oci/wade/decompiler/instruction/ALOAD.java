package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.LoadInstruction;

public class ALOAD extends LoadInstruction {
  public ALOAD(Opcode opcode) {
    super(opcode);
  }

  public ALOAD(Opcode opcode, int index) {
    super(opcode, index, false);
  }

  public ALOAD(Opcode opcode, int index, boolean wide) {
    super(opcode, index, wide);
  }
}
