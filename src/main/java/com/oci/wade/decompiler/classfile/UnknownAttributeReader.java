package com.oci.wade.decompiler.classfile;

import com.oci.wade.decompiler.attribute.Attribute;

public interface UnknownAttributeReader {
  Attribute createAttribute(int name_index, int length, java.io.DataInput file, ConstantPool constant_pool);
}
