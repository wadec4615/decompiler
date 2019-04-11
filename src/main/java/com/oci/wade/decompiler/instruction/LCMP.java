package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.generic.StackConsumer;
import com.oci.wade.decompiler.generic.StackProducer;
import com.oci.wade.decompiler.generic.TypedInstruction;

public class LCMP extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
  public LCMP() {
    super(Opcode.LCMP, (short) 1);
  }
}
