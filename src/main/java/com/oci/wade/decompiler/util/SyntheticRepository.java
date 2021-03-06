package com.oci.wade.decompiler.util;

import java.util.HashMap;
import java.util.Map;

public class SyntheticRepository extends MemorySensitiveClassPathRepository {
  private static Map<ClassPath, SyntheticRepository> instances = new HashMap<>();

  private SyntheticRepository(ClassPath path) {
    super(path);
  }

  public static SyntheticRepository getInstance() {
    return getInstance(ClassPath.SYSTEM_CLASS_PATH);
  }

  public static SyntheticRepository getInstance(ClassPath classPath) {
    SyntheticRepository rep = instances.get(classPath);
    if (rep == null) {
      rep = new SyntheticRepository(classPath);
      instances.put(classPath, rep);
    }
    return rep;
  }
}
