package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ArrayInstruction;
import com.oci.wade.decompiler.generic.StackConsumer;

public class FASTORE extends ArrayInstruction implements StackConsumer {
  public FASTORE() {
    super(Opcode.FASTORE);
  }
}
