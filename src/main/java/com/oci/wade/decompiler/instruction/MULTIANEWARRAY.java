package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.exception.ClassGenException;
import com.oci.wade.decompiler.generic.AllocationInstruction;
import com.oci.wade.decompiler.generic.CPInstruction;
import com.oci.wade.decompiler.generic.ExceptionConst;
import com.oci.wade.decompiler.generic.ExceptionThrower;
import com.oci.wade.decompiler.generic.LoadClass;

public class MULTIANEWARRAY extends CPInstruction implements LoadClass, AllocationInstruction, ExceptionThrower {
  private int dimensions;

  public MULTIANEWARRAY(int index, int dimensions) {
    super(Opcode.MULTIANEWARRAY, index);
    if (dimensions < 1) {
      throw new ClassGenException("Invalid dimensions value: " + dimensions);
    }
    this.dimensions = dimensions;
    super.setLength(4);
  }

  public int getDimensions() {
    return dimensions;
  }

  @Override
  public Class<?>[] getExceptions() {
    return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.ILLEGAL_ACCESS_ERROR, ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION);
  }

  @Override
  public String toString() {
    return super.toString() + " " + super.getIndex() + " " + dimensions;
  }

  @Override
  public String toString(ConstantPool cp) {
    return super.toString(cp) + " " + dimensions;
  }
}
