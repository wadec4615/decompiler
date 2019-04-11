package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.StoreInstruction;

public class DSTORE extends StoreInstruction {
  public DSTORE(Opcode opcode) {
    super(opcode);
  }

  public DSTORE(Opcode opcode, int n) {
    super(opcode, n, false);
  }

  public DSTORE(Opcode opcode, int n, boolean wide) {
    super(opcode, n, wide);
  }
}
