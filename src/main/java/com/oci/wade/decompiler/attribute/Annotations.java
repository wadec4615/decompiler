package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.classfile.AnnotationEntry;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.AttributeConstants;

public abstract class Annotations extends Attribute {
  private AnnotationEntry[] annotation_table;
  private boolean isRuntimeVisible;

  public Annotations(AttributeConstants annotation_type, int name_index, int length, AnnotationEntry[] annotation_table, ConstantPool constant_pool, boolean isRuntimeVisible) {
    super(annotation_type, name_index, length, constant_pool);
    this.annotation_table = annotation_table;
    this.isRuntimeVisible = isRuntimeVisible;
  }

  public Annotations(AttributeConstants annotation_type, int name_index, int length, DataInput input, ConstantPool constant_pool, boolean isRuntimeVisible) throws IOException {
    this(annotation_type, name_index, length, (AnnotationEntry[]) null, constant_pool, isRuntimeVisible);
    int annotation_table_length = input.readUnsignedShort();
    annotation_table = new AnnotationEntry[annotation_table_length];
    for (int i = 0; i < annotation_table_length; i++) {
      annotation_table[i] = AnnotationEntry.read(input, constant_pool, isRuntimeVisible);
    }
  }

  public AnnotationEntry[] getAnnotationEntries() {
    return annotation_table;
  }

  public int getNumAnnotations() {
    if (annotation_table == null) {
      return 0;
    }
    return annotation_table.length;
  }

  public boolean isRuntimeVisible() {
    return isRuntimeVisible;
  }

  public void setAnnotationTable(AnnotationEntry[] annotation_table) {
    this.annotation_table = annotation_table;
  }
}
