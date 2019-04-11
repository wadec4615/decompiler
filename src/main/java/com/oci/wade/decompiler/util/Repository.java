package com.oci.wade.decompiler.util;

import com.oci.wade.decompiler.classfile.JavaClass;

public interface Repository {
  void clear();

  JavaClass findClass(String className);

  ClassPath getClassPath();

  JavaClass loadClass(Class<?> clazz) throws ClassNotFoundException;

  JavaClass loadClass(String className) throws ClassNotFoundException;

  void removeClass(JavaClass clazz);

  void storeClass(JavaClass clazz);
}
