package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.StoreInstruction;

public class ASTORE extends StoreInstruction {
  public ASTORE(Opcode opcode) {
    super(opcode);
  }

  public ASTORE(Opcode opcode, int n) {
    super(opcode, n, false);
  }

  public ASTORE(Opcode opcode, int n, boolean wide) {
    super(opcode, n, wide);
  }
}
