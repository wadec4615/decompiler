package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.PushInstruction;
import com.oci.wade.decompiler.generic.StackInstruction;

public class DUP2 extends StackInstruction implements PushInstruction {
  public DUP2() {
    super(Opcode.DUP2);
  }
}
