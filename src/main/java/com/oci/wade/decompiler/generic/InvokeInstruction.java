package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class InvokeInstruction extends FieldOrMethod implements ExceptionThrower, StackConsumer, StackProducer {
    public InvokeInstruction(Opcode opcode, int index) {
	super(opcode, index);
    }
}
