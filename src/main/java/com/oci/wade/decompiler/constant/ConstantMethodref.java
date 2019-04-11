package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ConstantMethodref extends ConstantCP {
  public ConstantMethodref(ConstantMethodref c) {
    super(TypeConstants.CONSTANT_Methodref, c.getClassIndex(), c.getNameAndTypeIndex());
  }

  public ConstantMethodref(DataInput input) throws IOException {
    super(TypeConstants.CONSTANT_Methodref, input);
  }

  public ConstantMethodref(int class_index, int name_and_type_index) {
    super(TypeConstants.CONSTANT_Methodref, class_index, name_and_type_index);
  }
}
