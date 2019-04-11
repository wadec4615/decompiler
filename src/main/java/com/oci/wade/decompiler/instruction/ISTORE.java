package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.StoreInstruction;

public class ISTORE extends StoreInstruction {
  public ISTORE(Opcode opcode) {
    super(opcode);
  }

  public ISTORE(Opcode opcode, int n) {
    super(opcode, n, false);
  }

  public ISTORE(Opcode opcode, int n, boolean wide) {
    super(opcode, n, wide);
  }
}
