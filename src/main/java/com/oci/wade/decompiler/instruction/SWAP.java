package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.StackConsumer;
import com.oci.wade.decompiler.generic.StackInstruction;
import com.oci.wade.decompiler.generic.StackProducer;

public class SWAP extends StackInstruction implements StackConsumer, StackProducer {
  public SWAP() {
    super(Opcode.SWAP);
  }
}
