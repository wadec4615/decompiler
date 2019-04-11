package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.generic.StackConsumer;
import com.oci.wade.decompiler.generic.StackProducer;
import com.oci.wade.decompiler.generic.TypedInstruction;

public class FCMPG extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
  public FCMPG() {
    super(Opcode.FCMPG, (short) 1);
  }
}
