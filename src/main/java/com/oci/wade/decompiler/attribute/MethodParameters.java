package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.classfile.MethodParameter;
import com.oci.wade.decompiler.constants.AttributeConstants;

public class MethodParameters extends Attribute {
  private MethodParameter[] parameters = new MethodParameter[0];

  public MethodParameters(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    super(AttributeConstants.ATTR_METHOD_PARAMETERS, name_index, length, constant_pool);
    int parameters_count = input.readUnsignedByte();
    parameters = new MethodParameter[parameters_count];
    for (int i = 0; i < parameters_count; i++) {
      parameters[i] = new MethodParameter(input);
    }
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public MethodParameter[] getParameters() {
    return parameters;
  }

  public void setParameters(MethodParameter[] parameters) {
    this.parameters = parameters;
  }
}
