package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constant.ConstantClass;
import com.oci.wade.decompiler.constant.ConstantNameAndType;
import com.oci.wade.decompiler.constants.AttributeConstants;
import com.oci.wade.decompiler.constants.TypeConstants;

public class EnclosingMethod extends Attribute {
  private int classIndex;
  private int methodIndex;

  public EnclosingMethod(int nameIndex, int len, DataInput input, ConstantPool cpool) throws IOException {
    this(nameIndex, len, input.readUnsignedShort(), input.readUnsignedShort(), cpool);
  }

  private EnclosingMethod(int nameIndex, int len, int classIdx, int methodIdx, ConstantPool cpool) {
    super(AttributeConstants.ATTR_ENCLOSING_METHOD, nameIndex, len, cpool);
    classIndex = classIdx;
    methodIndex = methodIdx;
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public ConstantClass getEnclosingClass() {
    ConstantClass c = (ConstantClass) super.getConstantPool().getConstant(classIndex, TypeConstants.CONSTANT_Class);
    return c;
  }

  public int getEnclosingClassIndex() {
    return classIndex;
  }

  public ConstantNameAndType getEnclosingMethod() {
    if (methodIndex == 0) {
      return null;
    }
    ConstantNameAndType nat = (ConstantNameAndType) super.getConstantPool().getConstant(methodIndex, TypeConstants.CONSTANT_NameAndType);
    return nat;
  }

  public int getEnclosingMethodIndex() {
    return methodIndex;
  }

  public void setEnclosingClassIndex(int idx) {
    classIndex = idx;
  }

  public void setEnclosingMethodIndex(int idx) {
    methodIndex = idx;
  }
}
