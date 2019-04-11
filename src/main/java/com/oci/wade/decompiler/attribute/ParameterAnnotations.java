package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.AttributeConstants;

public abstract class ParameterAnnotations extends Attribute {
  private ParameterAnnotationEntry[] parameter_annotation_table;

  public ParameterAnnotations(AttributeConstants parameter_annotation_type, int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(parameter_annotation_type, name_index, length, (ParameterAnnotationEntry[]) null, constant_pool);
    int num_parameters = input.readUnsignedByte();
    parameter_annotation_table = new ParameterAnnotationEntry[num_parameters];
    for (int i = 0; i < num_parameters; i++) {
      parameter_annotation_table[i] = new ParameterAnnotationEntry(input, constant_pool);
    }
  }

  public ParameterAnnotations(AttributeConstants parameter_annotation_type, int name_index, int length, ParameterAnnotationEntry[] parameter_annotation_table, ConstantPool constant_pool) {
    super(parameter_annotation_type, name_index, length, constant_pool);
    this.parameter_annotation_table = parameter_annotation_table;
  }

  public ParameterAnnotationEntry[] getParameterAnnotationEntries() {
    return parameter_annotation_table;
  }

  public ParameterAnnotationEntry[] getParameterAnnotationTable() {
    return parameter_annotation_table;
  }

  public void setParameterAnnotationTable(ParameterAnnotationEntry[] parameter_annotation_table) {
    this.parameter_annotation_table = parameter_annotation_table;
  }
}
