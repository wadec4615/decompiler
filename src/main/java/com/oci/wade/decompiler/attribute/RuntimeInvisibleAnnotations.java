package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.AttributeConstants;

public class RuntimeInvisibleAnnotations extends Annotations {
  public RuntimeInvisibleAnnotations(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    super(AttributeConstants.ATTR_RUNTIME_INVISIBLE_ANNOTATIONS, name_index, length, input, constant_pool, false);
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }
}
