package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.StoreInstruction;

public class FSTORE extends StoreInstruction {
  public FSTORE(Opcode opcode) {
    super(opcode);
  }

  public FSTORE(Opcode opcode, int n) {
    super(opcode, n, false);
  }

  public FSTORE(Opcode opcode, int n, boolean wide) {
    super(opcode, n, wide);
  }
}
