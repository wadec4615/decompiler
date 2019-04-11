package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.AttributeConstants;
import com.oci.wade.decompiler.constants.TypeConstants;

public class PMGClass extends Attribute {
  private int pmg_class_index;
  private int pmg_index;

  public PMGClass(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, input.readUnsignedShort(), input.readUnsignedShort(), constant_pool);
  }

  public PMGClass(int name_index, int length, int pmg_index, int pmg_class_index, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_PMG, name_index, length, constant_pool);
    this.pmg_index = pmg_index;
    this.pmg_class_index = pmg_class_index;
  }

  public PMGClass(PMGClass c) {
    this(c.getNameIndex(), c.getLength(), c.getPMGIndex(), c.getPMGClassIndex(), c.getConstantPool());
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public int getPMGClassIndex() {
    return pmg_class_index;
  }

  public String getPMGClassName() {
    ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(pmg_class_index, TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public int getPMGIndex() {
    return pmg_index;
  }

  public String getPMGName() {
    ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(pmg_index, TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public void setPMGClassIndex(int pmg_class_index) {
    this.pmg_class_index = pmg_class_index;
  }

  public void setPMGIndex(int pmg_index) {
    this.pmg_index = pmg_index;
  }
}
