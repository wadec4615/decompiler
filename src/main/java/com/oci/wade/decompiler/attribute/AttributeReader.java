package com.oci.wade.decompiler.attribute;

import com.oci.wade.decompiler.classfile.ConstantPool;

public interface AttributeReader {
  Attribute createAttribute(int name_index, int length, java.io.DataInputStream file, ConstantPool constant_pool);
}
