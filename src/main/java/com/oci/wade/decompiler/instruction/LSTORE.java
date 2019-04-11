package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.StoreInstruction;

public class LSTORE extends StoreInstruction {
  public LSTORE(Opcode opcode) {
    super(opcode);
  }

  public LSTORE(Opcode opcode, int n) {
    super(opcode, n, false);
  }

  public LSTORE(Opcode opcode, int n, boolean wide) {
    super(opcode, n, wide);
  }
}
