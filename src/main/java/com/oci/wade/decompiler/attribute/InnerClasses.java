package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.AttributeConstants;

public class InnerClasses extends Attribute {
  private InnerClass[] inner_classes;

  public InnerClasses(InnerClasses c) {
    this(c.getNameIndex(), c.getLength(), c.getInnerClasses(), c.getConstantPool());
  }

  public InnerClasses(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, (InnerClass[]) null, constant_pool);
    int number_of_classes = input.readUnsignedShort();
    inner_classes = new InnerClass[number_of_classes];
    for (int i = 0; i < number_of_classes; i++) {
      inner_classes[i] = new InnerClass(input);
    }
  }

  public InnerClasses(int name_index, int length, InnerClass[] inner_classes, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_INNER_CLASSES, name_index, length, constant_pool);
    this.inner_classes = inner_classes != null ? inner_classes : new InnerClass[0];
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public InnerClass[] getInnerClasses() {
    return inner_classes;
  }

  public void setInnerClasses(InnerClass[] inner_classes) {
    this.inner_classes = inner_classes != null ? inner_classes : new InnerClass[0];
  }
}
