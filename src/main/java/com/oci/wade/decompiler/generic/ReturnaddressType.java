package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.DataTypeConstants;
import com.oci.wade.decompiler.type.Type;

public class ReturnaddressType extends Type {
  public static ReturnaddressType NO_TARGET = new ReturnaddressType();

  private ReturnaddressType() {
    super(DataTypeConstants.T_ADDRESS, "<return address>");
  }
}
