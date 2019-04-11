package com.oci.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;
import com.oci.wade.decompiler.constants.TypeConstants;
import com.oci.wade.decompiler.util.Utility;

public class BootstrapMethod implements Cloneable {
  private int bootstrap_method_ref;
  private int[] bootstrap_arguments;

  public BootstrapMethod(BootstrapMethod c) {
    this(c.getBootstrapMethodRef(), c.getBootstrapArguments());
  }

  public BootstrapMethod(DataInput input) throws IOException {
    this(input.readUnsignedShort(), input.readUnsignedShort());
    for (int i = 0; i < bootstrap_arguments.length; i++) {
      bootstrap_arguments[i] = input.readUnsignedShort();
    }
  }

  private BootstrapMethod(int bootstrap_method_ref, int num_bootstrap_arguments) {
    this(bootstrap_method_ref, new int[num_bootstrap_arguments]);
  }

  public BootstrapMethod(int bootstrap_method_ref, int[] bootstrap_arguments) {
    this.bootstrap_method_ref = bootstrap_method_ref;
    this.bootstrap_arguments = bootstrap_arguments;
  }

  public BootstrapMethod copy() {
    try {
      return (BootstrapMethod) clone();
    } catch (CloneNotSupportedException e) {
    }
    return null;
  }

  public int[] getBootstrapArguments() {
    return bootstrap_arguments;
  }

  public int getBootstrapMethodRef() {
    return bootstrap_method_ref;
  }

  public int getNumBootstrapArguments() {
    return bootstrap_arguments.length;
  }

  public void setBootstrapArguments(int[] bootstrap_arguments) {
    this.bootstrap_arguments = bootstrap_arguments;
  }

  public void setBootstrapMethodRef(int bootstrap_method_ref) {
    this.bootstrap_method_ref = bootstrap_method_ref;
  }

  @Override
  public String toString() {
    return "BootstrapMethod(" + bootstrap_method_ref + ", " + bootstrap_arguments.length + ", " + Arrays.toString(bootstrap_arguments) + ")";
  }

  public String toString(ConstantPool constant_pool) {
    StringBuilder buf = new StringBuilder();
    String bootstrap_method_name;
    bootstrap_method_name = constant_pool.constantToString(bootstrap_method_ref, TypeConstants.CONSTANT_MethodHandle);
    buf.append(Utility.compactClassName(bootstrap_method_name));
    int num_bootstrap_arguments = bootstrap_arguments.length;
    if (num_bootstrap_arguments > 0) {
      buf.append("\n     Method Arguments:");
      for (int i = 0; i < num_bootstrap_arguments; i++) {
        buf.append("\n     ").append(i).append(": ");
        buf.append(constant_pool.constantToString(constant_pool.getConstant(bootstrap_arguments[i])));
      }
    }
    return buf.toString();
  }
}
