package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.StackInstruction;

public class DUP_X2 extends StackInstruction {
  public DUP_X2() {
    super(Opcode.DUP_X2);
  }
}
