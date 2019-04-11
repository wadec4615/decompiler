package com.oci.wade.decompiler.type;

import com.oci.wade.decompiler.classfile.JavaClass;
import com.oci.wade.decompiler.constants.DataTypeConstants;
import com.oci.wade.decompiler.util.RepositoryImpl;

public class ObjectType extends ReferenceType {
  private String class_name;

  public ObjectType(String class_name) {
    super(DataTypeConstants.T_REFERENCE, "L" + class_name.replace('.', '/') + ";");
    this.class_name = class_name.replace('/', '.');
  }

  public boolean accessibleTo(ObjectType accessor) throws ClassNotFoundException {
    JavaClass jc = RepositoryImpl.lookupClass(class_name);
    if (jc.isPublic()) {
      return true;
    }
    JavaClass acc = RepositoryImpl.lookupClass(accessor.class_name);
    return acc.getPackageName().equals(jc.getPackageName());
  }

  @Override
  public boolean equals(Object type) {
    return type instanceof ObjectType ? ((ObjectType) type).class_name.equals(class_name) : false;
  }

  public String getClassName() {
    return class_name;
  }

  @Override
  public int hashCode() {
    return class_name.hashCode();
  }

  @Deprecated
  public boolean referencesClass() {
    try {
      JavaClass jc = RepositoryImpl.lookupClass(class_name);
      return jc.isClass();
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

  public boolean referencesClassExact() throws ClassNotFoundException {
    JavaClass jc = RepositoryImpl.lookupClass(class_name);
    return jc.isClass();
  }

  @Deprecated
  public boolean referencesInterface() {
    try {
      JavaClass jc = RepositoryImpl.lookupClass(class_name);
      return !jc.isClass();
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

  public boolean referencesInterfaceExact() throws ClassNotFoundException {
    JavaClass jc = RepositoryImpl.lookupClass(class_name);
    return !jc.isClass();
  }

  public static ObjectType getInstance(String class_name) {
    return new ObjectType(class_name);
  }
}
