package com.oci.wade.decompiler.classfile;

public class AnnotationElementValue extends ElementValue {
  private AnnotationEntry annotationEntry;

  public AnnotationElementValue(int type, AnnotationEntry annotationEntry, ConstantPool cpool) {
    super(type, cpool);
    if (type != ANNOTATION) {
      throw new RuntimeException("Only element values of type annotation can be built with this ctor - type specified: " + type);
    }
    this.annotationEntry = annotationEntry;
  }

  public AnnotationEntry getAnnotationEntry() {
    return annotationEntry;
  }
}
