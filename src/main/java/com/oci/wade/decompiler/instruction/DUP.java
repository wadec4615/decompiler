package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.PushInstruction;
import com.oci.wade.decompiler.generic.StackInstruction;

public class DUP extends StackInstruction implements PushInstruction {
  public DUP() {
    super(Opcode.DUP);
  }
}
