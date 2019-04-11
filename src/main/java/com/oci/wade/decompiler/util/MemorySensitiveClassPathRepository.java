package com.oci.wade.decompiler.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import com.oci.wade.decompiler.classfile.ClassParser;
import com.oci.wade.decompiler.classfile.JavaClass;

public class MemorySensitiveClassPathRepository implements Repository {
  private ClassPath path = null;
  private Map<String, SoftReference<JavaClass>> loadedClasses = new HashMap<>();

  public MemorySensitiveClassPathRepository(ClassPath path) {
    this.path = path;
  }

  @Override
  public void clear() {
    loadedClasses.clear();
  }

  @Override
  public JavaClass findClass(String className) {
    SoftReference<JavaClass> ref = loadedClasses.get(className);
    if (ref == null) {
      return null;
    }
    return ref.get();
  }

  @Override
  public ClassPath getClassPath() {
    return path;
  }

  @Override
  public JavaClass loadClass(Class<?> clazz) throws ClassNotFoundException {
    String className = clazz.getName();
    JavaClass repositoryClass = findClass(className);
    if (repositoryClass != null) {
      return repositoryClass;
    }
    String name = className;
    int i = name.lastIndexOf('.');
    if (i > 0) {
      name = name.substring(i + 1);
    }
    JavaClass cls = null;
    try (InputStream clsStream = clazz.getResourceAsStream(name + ".class")) {
      return cls = loadClass(clsStream, className);
    } catch (Exception e) {
      return cls;
    }
  }

  @Override
  public JavaClass loadClass(String className) throws ClassNotFoundException {
    if ((className == null) || className.isEmpty()) {
      throw new IllegalArgumentException("Invalid class name " + className);
    }
    className = className.replace('/', '.');
    JavaClass clazz = findClass(className);
    if (clazz != null) {
      return clazz;
    }
    try {
      return loadClass(path.getInputStream(className), className);
    } catch (Exception e) {
      throw new ClassNotFoundException("Exception while looking for class " + className + ": " + e, e);
    }
  }

  @Override
  public void removeClass(JavaClass clazz) {
    loadedClasses.remove(clazz.getClassName());
  }

  @Override
  public void storeClass(JavaClass clazz) {
    loadedClasses.put(clazz.getClassName(), new SoftReference<>(clazz));
    clazz.setRepository(this);
  }

  private JavaClass loadClass(InputStream is, String className) throws Exception {
    try {
      if (is != null) {
        ClassParser parser = new ClassParser(is, className);
        JavaClass clazz = parser.parse();
        storeClass(clazz);
        return clazz;
      }
    } catch (IOException e) {
      throw new ClassNotFoundException("Exception while looking for class " + className + ": " + e, e);
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
        }
      }
    }
    throw new ClassNotFoundException("SyntheticRepository could not load " + className);
  }
}
