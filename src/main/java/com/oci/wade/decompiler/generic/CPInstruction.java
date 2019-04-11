package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constant.Constant;
import com.oci.wade.decompiler.constant.ConstantClass;
import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.exception.ClassGenException;

public abstract class CPInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
  protected int index;

  public CPInstruction(Opcode opcode, int index) {
    super(opcode, 3);
    setIndex(index);
  }

  @Override
  public int getIndex() {
    return index;
  }

  @Override
  public void setIndex(int index) {
    if (index < 0) {
      throw new ClassGenException("Negative index value: " + index);
    }
    this.index = index;
  }

  @Override
  public String toString() {
    return super.toString() + " " + index;
  }

  @Override
  public String toString(ConstantPool cp) {
    Constant c = cp.getConstant(index);
    String str = cp.constantToString(c);
    if (c instanceof ConstantClass) {
      str = str.replace('.', '/');
    }
    return Opcode.getOpcodeName(super.getOpcode()) + " " + str;
  }
}
