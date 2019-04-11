package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class ArithmeticInstruction extends Instruction implements TypedInstruction, StackProducer, StackConsumer {
  public ArithmeticInstruction(Opcode opcode) {
    super(opcode, 1);
  }
}
