package com.oci.wade.decompiler.constant;

import com.oci.wade.decompiler.classfile.ConstantPool;

public interface ConstantObject {
  Object getConstantValue(ConstantPool cp);
}
