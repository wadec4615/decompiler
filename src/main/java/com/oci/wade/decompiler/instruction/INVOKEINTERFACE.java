package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.exception.ClassGenException;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.InvokeInstruction;

public class INVOKEINTERFACE extends InvokeInstruction {
  private int nargs;

  public INVOKEINTERFACE(ConstantPool constantPool, int index, int nargs) {
    super(constantPool, Opcode.INVOKEINTERFACE, index);
    super.setLength(5);
    if (nargs < 1) {
      throw new ClassGenException("Number of arguments must be > 0 " + nargs);
    }
    this.nargs = nargs;
  }

  public int getCount() {
    return nargs;
  }

  @Override
  public Class<?>[] getExceptions() {
    return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION, ExceptionConst.UNSATISFIED_LINK_ERROR, ExceptionConst.ABSTRACT_METHOD_ERROR, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
  }

  @Override
  public String toString(ConstantPool cp) {
    return super.toString(cp) + " " + nargs;
  }
}
