package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constant.ConstantUtf8;
import com.oci.wade.decompiler.constants.AttributeConstants;
import com.oci.wade.decompiler.constants.TypeConstants;

public class SourceFile extends Attribute {
  private int sourcefile_index;

  public SourceFile(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, input.readUnsignedShort(), constant_pool);
  }

  public SourceFile(int name_index, int length, int sourcefile_index, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_SOURCE_FILE, name_index, length, constant_pool);
    this.sourcefile_index = sourcefile_index;
  }

  public SourceFile(SourceFile c) {
    this(c.getNameIndex(), c.getLength(), c.getSourceFileIndex(), c.getConstantPool());
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // nothing to output
  }

  public int getSourceFileIndex() {
    return sourcefile_index;
  }

  public String getSourceFileName() {
    ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(sourcefile_index, TypeConstants.CONSTANT_Utf8);
    return c.getBytes();
  }

  public void setSourceFileIndex(int sourcefile_index) {
    this.sourcefile_index = sourcefile_index;
  }
}
