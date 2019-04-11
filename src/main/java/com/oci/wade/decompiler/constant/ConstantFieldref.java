package com.oci.wade.decompiler.constant;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.constants.TypeConstants;

public class ConstantFieldref extends ConstantCP {
  public ConstantFieldref(ConstantFieldref c) {
    super(TypeConstants.CONSTANT_Fieldref, c.getClassIndex(), c.getNameAndTypeIndex());
  }

  public ConstantFieldref(DataInput input) throws IOException {
    super(TypeConstants.CONSTANT_Fieldref, input);
  }

  public ConstantFieldref(int class_index, int name_and_type_index) {
    super(TypeConstants.CONSTANT_Fieldref, class_index, name_and_type_index);
  }
}
