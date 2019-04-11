package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ArrayInstruction;
import com.oci.wade.decompiler.generic.StackProducer;

public class DALOAD extends ArrayInstruction implements StackProducer {
  public DALOAD() {
    super(Opcode.DALOAD);
  }
}
