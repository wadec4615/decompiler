package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.InvokeInstruction;

public class INVOKEDYNAMIC extends InvokeInstruction {
  public INVOKEDYNAMIC(ConstantPool constantPool, int index) {
    super(constantPool, Opcode.INVOKEDYNAMIC, index);
  }

  @Override
  public Class<?>[] getExceptions() {
    return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
  }
}
