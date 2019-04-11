package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ConstantInterfaceMethodref extends ConstantCP {
  public ConstantInterfaceMethodref(ConstantInterfaceMethodref c) {
    super(TypeConstants.CONSTANT_InterfaceMethodref, c.getClassIndex(), c.getNameAndTypeIndex());
  }

  public ConstantInterfaceMethodref(DataInput input) throws IOException {
    super(TypeConstants.CONSTANT_InterfaceMethodref, input);
  }

  public ConstantInterfaceMethodref(int class_index, int name_and_type_index) {
    super(TypeConstants.CONSTANT_InterfaceMethodref, class_index, name_and_type_index);
  }
}
