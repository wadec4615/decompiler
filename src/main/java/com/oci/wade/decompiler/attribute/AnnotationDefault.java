package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.classfile.ElementValue;
import com.oci.wade.decompiler.constants.AttributeConstants;

public class AnnotationDefault extends Attribute {
  private ElementValue default_value;

  public AnnotationDefault(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, (ElementValue) null, constant_pool);
    default_value = ElementValue.readElementValue(input, constant_pool);
  }

  public AnnotationDefault(int name_index, int length, ElementValue defaultValue, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_ANNOTATION_DEFAULT, name_index, length, constant_pool);
    this.default_value = defaultValue;
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public ElementValue getDefaultValue() {
    return default_value;
  }

  public void setDefaultValue(ElementValue defaultValue) {
    default_value = defaultValue;
  }
}
