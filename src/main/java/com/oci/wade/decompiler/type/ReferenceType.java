package com.oci.wade.decompiler.type;

import com.oci.wade.decompiler.constants.DataTypeConstants;

public abstract class ReferenceType extends Type {
  public ReferenceType() {
    super(DataTypeConstants.T_OBJECT, "<null object>");
  }

  public ReferenceType(DataTypeConstants t, String s) {
    super(t, s);
  }
}
