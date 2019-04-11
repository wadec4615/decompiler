package com.oci.wade.decompiler.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import com.oci.wade.decompiler.classfile.ClassParser;
import com.oci.wade.decompiler.classfile.JavaClass;

public class ClassPathRepository implements Repository {
  private ClassPath _path = null;
  private Map<String, JavaClass> _loadedClasses = new HashMap<>();

  public ClassPathRepository(ClassPath path) {
    _path = path;
  }

  @Override
  public void clear() {
    _loadedClasses.clear();
  }

  @Override
  public JavaClass findClass(String className) {
    return _loadedClasses.get(className);
  }

  @Override
  public ClassPath getClassPath() {
    return _path;
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
    } catch (IOException e) {
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
      return loadClass(_path.getInputStream(className), className);
    } catch (IOException e) {
      throw new ClassNotFoundException("Exception while looking for class " + className + ": " + e, e);
    }
  }

  @Override
  public void removeClass(JavaClass clazz) {
    _loadedClasses.remove(clazz.getClassName());
  }

  @Override
  public void storeClass(JavaClass clazz) {
    _loadedClasses.put(clazz.getClassName(), clazz);
    clazz.setRepository(this);
  }

  private JavaClass loadClass(InputStream is, String className) throws ClassNotFoundException {
    try {
      if (is != null) {
        ClassParser parser = new ClassParser(is, className);
        JavaClass clazz = parser.parse();
        storeClass(clazz);
        return clazz;
      }
    } catch (Exception e) {
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
