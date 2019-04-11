package com.oci.wade.decompiler.generic;

import java.util.StringTokenizer;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constant.Constant;
import com.oci.wade.decompiler.constants.Opcode;

public abstract class InvokeInstruction extends FieldOrMethod implements ExceptionThrower, StackConsumer, StackProducer {
  private final String parameter;
  private final String format;

  public InvokeInstruction(ConstantPool constantPool, Opcode opcode, int index) {
    super(opcode, index);
    Constant c = constantPool.getConstant(index);
    String str = constantPool.constantToString(c);
    StringTokenizer tok = new StringTokenizer(str, ":");
    parameter = tok.nextToken();
    format = tok.nextToken();
  }

  public String getFormat() {
    return format;
  }

  public String getParameter() {
    return parameter;
  }
}
