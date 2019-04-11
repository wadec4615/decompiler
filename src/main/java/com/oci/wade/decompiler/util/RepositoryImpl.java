package com.oci.wade.decompiler.util;

import com.oci.wade.decompiler.classfile.JavaClass;

public abstract class RepositoryImpl {
  private static com.oci.wade.decompiler.util.Repository repository = SyntheticRepository.getInstance();

  public static JavaClass lookupClass(String class_name) throws ClassNotFoundException {
    return repository.loadClass(class_name);
  }
}
