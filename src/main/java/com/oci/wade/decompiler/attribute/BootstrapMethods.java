package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.BootstrapMethod;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.AttributeConstants;

public class BootstrapMethods extends Attribute {
  private BootstrapMethod[] bootstrap_methods;

  public BootstrapMethods(BootstrapMethods c) {
    this(c.getNameIndex(), c.getLength(), c.getBootstrapMethods(), c.getConstantPool());
  }

  public BootstrapMethods(int name_index, int length, BootstrapMethod[] bootstrap_methods, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_BOOTSTRAP_METHODS, name_index, length, constant_pool);
    this.bootstrap_methods = bootstrap_methods;
  }

  public BootstrapMethods(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, (BootstrapMethod[]) null, constant_pool);
    int num_bootstrap_methods = input.readUnsignedShort();
    bootstrap_methods = new BootstrapMethod[num_bootstrap_methods];
    for (int i = 0; i < num_bootstrap_methods; i++) {
      bootstrap_methods[i] = new BootstrapMethod(input);
    }
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public BootstrapMethod[] getBootstrapMethods() {
    return bootstrap_methods;
  }

  public void setBootstrapMethods(BootstrapMethod[] bootstrap_methods) {
    this.bootstrap_methods = bootstrap_methods;
  }
}
