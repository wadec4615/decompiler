package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.constants.TypeConstants;

public abstract class FieldInstruction extends FieldOrMethod {
  public FieldInstruction(Opcode opcode, int index) {
    super(opcode, index);
  }

  @Override
  public String toString(ConstantPool cp) {
    return Opcode.getOpcodeName(super.getOpcode()) + " " + cp.constantToString(super.getIndex(), TypeConstants.CONSTANT_Fieldref);
  }
}
