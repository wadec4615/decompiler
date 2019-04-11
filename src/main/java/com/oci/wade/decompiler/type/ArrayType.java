package com.oci.wade.decompiler.type;

import com.oci.wade.decompiler.constants.Const;
import com.oci.wade.decompiler.constants.DataTypeConstants;
import com.oci.wade.decompiler.exception.ClassGenException;

public class ArrayType extends ReferenceType {
  private int dimensions;
  private Type basic_type;

  public ArrayType(DataTypeConstants type, int dimensions) {
    this(BasicType.getType(type), dimensions);
  }

  public ArrayType(String class_name, int dimensions) {
    this(ObjectType.getInstance(class_name), dimensions);
  }

  public ArrayType(Type type, int dimensions) {
    super(DataTypeConstants.T_ARRAY, "<dummy>");
    if (dimensions < 1 || dimensions > Const.MAX_BYTE) {
      throw new ClassGenException("Invalid number of dimensions: " + dimensions);
    }
    switch (type.getType()) {
      case T_ARRAY:
        ArrayType array = (ArrayType) type;
        this.dimensions = dimensions + array.dimensions;
        basic_type = array.basic_type;
        break;
      case T_VOID:
        throw new ClassGenException("Invalid type: void[]");
      default:
        this.dimensions = dimensions;
        basic_type = type;
        break;
    }
    StringBuilder buf = new StringBuilder();
    for (int i = 0; i < this.dimensions; i++) {
      buf.append('[');
    }
    buf.append(basic_type.getSignature());
    super.setSignature(buf.toString());
  }

  @Override
  public boolean equals(Object _type) {
    if (_type instanceof ArrayType) {
      ArrayType array = (ArrayType) _type;
      return array.dimensions == dimensions && array.basic_type.equals(basic_type);
    }
    return false;
  }

  public Type getBasicType() {
    return basic_type;
  }

  public int getDimensions() {
    return dimensions;
  }

  public Type getElementType() {
    if (dimensions == 1) {
      return basic_type;
    }
    return new ArrayType(basic_type, dimensions - 1);
  }

  @Override
  public int hashCode() {
    return basic_type.hashCode() ^ dimensions;
  }
}
