package com.oci.wade.decompiler.classfile;

import java.util.ArrayList;
import java.util.List;
import com.oci.wade.decompiler.attribute.Attribute;
import com.oci.wade.decompiler.attribute.InnerClass;
import com.oci.wade.decompiler.attribute.InnerClasses;
import com.oci.wade.decompiler.attribute.SourceFile;
//import com.oci.wade.decompiler.backup.ClassQueue;
import com.oci.wade.decompiler.constants.AccessConstants;
import com.oci.wade.decompiler.constants.TypeConstants;
import com.oci.wade.decompiler.type.Type;
import com.oci.wade.decompiler.util.Repository;
import com.oci.wade.decompiler.util.SyntheticRepository;
import com.oci.wade.decompiler.util.Utility;

public class JavaClass extends AccessFlags implements Cloneable, Comparable<JavaClass> {
  public static byte HEAP = 1;
  public static byte FILE = 2;
  public static byte ZIP = 3;
  private String file_name;
  private String package_name;
  private String source_file_name = "<Unknown>";
  private int class_name_index;
  private int superclass_name_index;
  private String class_name;
  private String superclass_name;
  private int major;
  private int minor;
  private ConstantPool constant_pool;
  private int[] interfaces;
  private String[] interface_names;
  private Field[] fields;
  private Method[] methods;
  private Attribute[] attributes;
  private AnnotationEntry[] annotations;
  private byte source = HEAP;
  private boolean isAnonymous = false;
  private boolean isNested = false;
  private boolean computedNestedTypeStatus = false;
  private transient Repository repository = SyntheticRepository.getInstance();

  public JavaClass(int class_name_index, int superclass_name_index, String file_name, int major, int minor, int access_flags, ConstantPool constant_pool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes) {
    this(class_name_index, superclass_name_index, file_name, major, minor, access_flags, constant_pool, interfaces, fields, methods, attributes, HEAP);
  }

  public JavaClass(int class_name_index, int superclass_name_index, String file_name, int major, int minor, int access_flags, ConstantPool constant_pool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes, byte source) {
    super(access_flags);
    if (interfaces == null) {
      interfaces = new int[0];
    }
    if (attributes == null) {
      attributes = new Attribute[0];
    }
    if (fields == null) {
      fields = new Field[0];
    }
    if (methods == null) {
      methods = new Method[0];
    }
    this.class_name_index = class_name_index;
    this.superclass_name_index = superclass_name_index;
    this.file_name = file_name;
    this.major = major;
    this.minor = minor;
    this.constant_pool = constant_pool;
    this.interfaces = interfaces;
    this.fields = fields;
    this.methods = methods;
    this.attributes = attributes;
    this.source = source;
    for (Attribute attribute : attributes) {
      if (attribute instanceof SourceFile) {
        source_file_name = ((SourceFile) attribute).getSourceFileName();
        break;
      }
    }
    class_name = constant_pool.getConstantString(class_name_index, TypeConstants.CONSTANT_Class);
    class_name = Utility.compactClassName(class_name, false);
    int index = class_name.lastIndexOf('.');
    if (index < 0) {
      package_name = "";
    } else {
      package_name = class_name.substring(0, index);
    }
    if (superclass_name_index > 0) {
      superclass_name = constant_pool.getConstantString(superclass_name_index, TypeConstants.CONSTANT_Class);
      superclass_name = Utility.compactClassName(superclass_name, false);
    } else {
      superclass_name = "java.lang.Object";
    }
    interface_names = new String[interfaces.length];
    for (int i = 0; i < interfaces.length; i++) {
      String str = constant_pool.getConstantString(interfaces[i], TypeConstants.CONSTANT_Class);
      interface_names[i] = Utility.compactClassName(str, false);
    }
  }

  @Override
  public int compareTo(JavaClass obj) {
    return getClassName().compareTo(obj.getClassName());
  }

  public AnnotationEntry[] getAnnotationEntries() {
    if (annotations == null) {
      annotations = AnnotationEntry.createAnnotationEntries(getAttributes());
    }
    return annotations;
  }

  public Attribute[] getAttributes() {
    return attributes;
  }

  public String getClassName() {
    return class_name;
  }

  public int getClassNameIndex() {
    return class_name_index;
  }

  public ConstantPool getConstantPool() {
    return constant_pool;
  }

  public Field[] getFields() {
    return fields;
  }

  public String getFileName() {
    return file_name;
  }

  public int[] getInterfaceIndices() {
    return interfaces;
  }

  public String[] getInterfaceNames() {
    return interface_names;
  }

  public JavaClass[] getInterfaces() throws ClassNotFoundException {
    String[] _interfaces = getInterfaceNames();
    JavaClass[] classes = new JavaClass[_interfaces.length];
    for (int i = 0; i < _interfaces.length; i++) {
      classes[i] = repository.loadClass(_interfaces[i]);
    }
    return classes;
  }

  public int getMajor() {
    return major;
  }

  public Method getMethod(java.lang.reflect.Method m) {
    for (Method method : methods) {
      if (m.getName().equals(method.getName()) && (m.getModifiers() == method.getModifiers()) && Type.getSignature(m).equals(method.getSignature())) {
        return method;
      }
    }
    return null;
  }

  public Method[] getMethods() {
    return methods;
  }

  public int getMinor() {
    return minor;
  }

  public String getPackageName() {
    return package_name;
  }

  public com.oci.wade.decompiler.util.Repository getRepository() {
    return repository;
  }

  public byte getSource() {
    return source;
  }

  public String getSourceFileName() {
    return source_file_name;
  }

  public JavaClass getSuperClass() throws ClassNotFoundException {
    if ("java.lang.Object".equals(getClassName())) {
      return null;
    }
    return repository.loadClass(getSuperclassName());
  }

  public JavaClass[] getSuperClasses() throws ClassNotFoundException {
    JavaClass clazz = this;
    List<JavaClass> allSuperClasses = new ArrayList<>();
    for (clazz = clazz.getSuperClass(); clazz != null; clazz = clazz.getSuperClass()) {
      allSuperClasses.add(clazz);
    }
    return allSuperClasses.toArray(new JavaClass[allSuperClasses.size()]);
  }

  public String getSuperclassName() {
    return superclass_name;
  }

  public int getSuperclassNameIndex() {
    return superclass_name_index;
  }

  public boolean isAnonymous() {
    computeNestedTypeStatus();
    return this.isAnonymous;
  }

  public boolean isClass() {
    return (super.getAccessFlags() & AccessConstants.ACC_INTERFACE.getValue()) == 0;
  }

  public boolean isNested() {
    computeNestedTypeStatus();
    return this.isNested;
  }

  public boolean isSuper() {
    return (super.getAccessFlags() & AccessConstants.ACC_SUPER.getValue()) != 0;
  }

  public void setAttributes(Attribute[] attributes) {
    this.attributes = attributes;
  }

  public void setClassName(String class_name) {
    this.class_name = class_name;
  }

  public void setClassNameIndex(int class_name_index) {
    this.class_name_index = class_name_index;
  }

  public void setConstantPool(ConstantPool constant_pool) {
    this.constant_pool = constant_pool;
  }

  public void setFields(Field[] fields) {
    this.fields = fields;
  }

  public void setFileName(String file_name) {
    this.file_name = file_name;
  }

  public void setInterfaceNames(String[] interface_names) {
    this.interface_names = interface_names;
  }

  public void setInterfaces(int[] interfaces) {
    this.interfaces = interfaces;
  }

  public void setMajor(int major) {
    this.major = major;
  }

  public void setMethods(Method[] methods) {
    this.methods = methods;
  }

  public void setMinor(int minor) {
    this.minor = minor;
  }

  public void setRepository(com.oci.wade.decompiler.util.Repository repository) {
    this.repository = repository;
  }

  public void setSourceFileName(String source_file_name) {
    this.source_file_name = source_file_name;
  }

  public void setSuperclassName(String superclass_name) {
    this.superclass_name = superclass_name;
  }

  public void setSuperclassNameIndex(int superclass_name_index) {
    this.superclass_name_index = superclass_name_index;
  }

  private void computeNestedTypeStatus() {
    if (computedNestedTypeStatus) {
      return;
    }
    for (Attribute attribute : this.attributes) {
      if (attribute instanceof InnerClasses) {
        InnerClass[] innerClasses = ((InnerClasses) attribute).getInnerClasses();
        for (InnerClass innerClasse : innerClasses) {
          boolean innerClassAttributeRefersToMe = false;
          String inner_class_name = constant_pool.getConstantString(innerClasse.getInnerClassIndex(), TypeConstants.CONSTANT_Class);
          inner_class_name = Utility.compactClassName(inner_class_name);
          if (inner_class_name.equals(getClassName())) {
            innerClassAttributeRefersToMe = true;
          }
          if (innerClassAttributeRefersToMe) {
            this.isNested = true;
            if (innerClasse.getInnerNameIndex() == 0) {
              this.isAnonymous = true;
            }
          }
        }
      }
    }
    this.computedNestedTypeStatus = true;
  }
}
