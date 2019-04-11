package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.type.Type;

public interface NamedAndTyped {
  String getName();

  Type getType();

  void setName(String name);

  void setType(Type type);
}
