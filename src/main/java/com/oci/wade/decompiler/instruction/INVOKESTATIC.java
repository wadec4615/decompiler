package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.InvokeInstruction;

public class INVOKESTATIC extends InvokeInstruction {
    public INVOKESTATIC(int index) {
	super(Opcode.INVOKESTATIC, index);
    }

    @Override
    public Class<?>[] getExceptions() {
	return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }
}
