package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.oci.wade.decompiler.classfile.AnnotationEntry;
import com.oci.wade.decompiler.classfile.ConstantPool;

public class ParameterAnnotationEntry {
  private AnnotationEntry[] annotation_table;

  public ParameterAnnotationEntry(DataInput input, ConstantPool constant_pool) throws IOException {
    int annotation_table_length = input.readUnsignedShort();
    annotation_table = new AnnotationEntry[annotation_table_length];
    for (int i = 0; i < annotation_table_length; i++) {
      annotation_table[i] = AnnotationEntry.read(input, constant_pool, false);
    }
  }

  public AnnotationEntry[] getAnnotationEntries() {
    return annotation_table;
  }

  public static ParameterAnnotationEntry[] createParameterAnnotationEntries(Attribute[] attrs) {
    List<ParameterAnnotationEntry> accumulatedAnnotations = new ArrayList<>(attrs.length);
    for (Attribute attribute : attrs) {
      if (attribute instanceof ParameterAnnotations) {
        ParameterAnnotations runtimeAnnotations = (ParameterAnnotations) attribute;
        Collections.addAll(accumulatedAnnotations, runtimeAnnotations.getParameterAnnotationEntries());
      }
    }
    return accumulatedAnnotations.toArray(new ParameterAnnotationEntry[accumulatedAnnotations.size()]);
  }
}
