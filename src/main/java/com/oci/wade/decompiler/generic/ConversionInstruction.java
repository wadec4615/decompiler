package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class ConversionInstruction extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
  public ConversionInstruction(Opcode opcode) {
    super(opcode, 1);
  }
}
