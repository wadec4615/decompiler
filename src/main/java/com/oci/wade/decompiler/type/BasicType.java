package com.oci.wade.decompiler.type;

import com.oci.wade.decompiler.constants.DataTypeConstants;
import com.oci.wade.decompiler.exception.ClassGenException;

public class BasicType extends Type {
  public BasicType(DataTypeConstants type) {
    super(type, DataTypeConstants.getShortTypeName(type.getTag()));
    if (type.getTag() < DataTypeConstants.T_BOOLEAN.getTag() || type.getTag() > DataTypeConstants.T_VOID.getTag()) {
      throw new ClassGenException("Invalid type: " + type);
    }
  }

  @Override
  public boolean equals(Object _type) {
    return _type instanceof BasicType ? ((BasicType) _type).getType() == this.getType() : false;
  }

  public static BasicType getType(DataTypeConstants type) {
    switch (type) {
      case T_VOID:
        return VOID;
      case T_BOOLEAN:
        return BOOLEAN;
      case T_BYTE:
        return BYTE;
      case T_SHORT:
        return SHORT;
      case T_CHAR:
        return CHAR;
      case T_INT:
        return INT;
      case T_LONG:
        return LONG;
      case T_DOUBLE:
        return DOUBLE;
      case T_FLOAT:
        return FLOAT;
      default:
        throw new ClassGenException("Invalid type: " + type);
    }
  }
}
