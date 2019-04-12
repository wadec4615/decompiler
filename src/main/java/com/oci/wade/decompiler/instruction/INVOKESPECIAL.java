package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.InvokeInstruction;

public class INVOKESPECIAL extends InvokeInstruction {
    public INVOKESPECIAL(int index) {
	super(Opcode.INVOKESPECIAL, index);
    }

    @Override
    public Class<?>[] getExceptions() {
	return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_FIELD_AND_METHOD_RESOLUTION, ExceptionConst.NULL_POINTER_EXCEPTION, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.UNSATISFIED_LINK_ERROR);
    }
}
