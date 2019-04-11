package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constant.Constant;
import com.oci.wade.decompiler.constant.ConstantDouble;
import com.oci.wade.decompiler.constant.ConstantFloat;
import com.oci.wade.decompiler.constant.ConstantInteger;
import com.oci.wade.decompiler.constant.ConstantLong;
import com.oci.wade.decompiler.constant.ConstantString;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.AttributeConstants;
import com.oci.wade.decompiler.constants.TypeConstants;
import com.oci.wade.decompiler.util.Utility;

public class ConstantValue extends Attribute {
  private int constantvalue_index;

  public ConstantValue(ConstantValue c) {
    this(c.getNameIndex(), c.getLength(), c.getConstantValueIndex(), c.getConstantPool());
  }

  public ConstantValue(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, input.readUnsignedShort(), constant_pool);
  }

  public ConstantValue(int name_index, int length, int constantvalue_index, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_CONSTANT_VALUE, name_index, length, constant_pool);
    this.constantvalue_index = constantvalue_index;
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    Constant c = super.getConstantPool().getConstant(constantvalue_index);
    switch (c.getTag()) {
      case CONSTANT_Long:
        out.print(String.valueOf(((ConstantLong) c).getBytes()));
        break;
      case CONSTANT_Float:
        out.print(String.valueOf(((ConstantFloat) c).getBytes()));
        break;
      case CONSTANT_Double:
        out.print(String.valueOf(((ConstantDouble) c).getBytes()));
        break;
      case CONSTANT_Integer:
        out.print(String.valueOf(((ConstantInteger) c).getBytes()));
        break;
      case CONSTANT_String:
        int i = ((ConstantString) c).getStringIndex();
        c = super.getConstantPool().getConstant(i, TypeConstants.CONSTANT_Utf8);
        out.print("\"" + Utility.convertString(((ConstantUtf8) c).getBytes()) + "\"");
        break;
      default:
        throw new IllegalStateException("Type of ConstValue invalid: " + c);
    }
  }

  public int getConstantValueIndex() {
    return constantvalue_index;
  }

  public void setConstantValueIndex(int constantvalue_index) {
    this.constantvalue_index = constantvalue_index;
  }
}
