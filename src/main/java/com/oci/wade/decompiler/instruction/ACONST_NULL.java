package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.generic.PushInstruction;
import com.oci.wade.decompiler.generic.TypedInstruction;

public class ACONST_NULL extends Instruction implements PushInstruction, TypedInstruction {
  public ACONST_NULL() {
    super(Opcode.ACONST_NULL, (short) 1);
  }
}
