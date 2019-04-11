package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.StackInstruction;

public class DUP2_X1 extends StackInstruction {
  public DUP2_X1() {
    super(Opcode.DUP2_X1);
  }
}
