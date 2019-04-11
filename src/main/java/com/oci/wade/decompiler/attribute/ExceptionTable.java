package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.AttributeConstants;
import com.oci.wade.decompiler.constants.TypeConstants;
import com.oci.wade.decompiler.util.Utility;

public class ExceptionTable extends Attribute {
  private int[] exception_index_table;

  public ExceptionTable(ExceptionTable c) {
    this(c.getNameIndex(), c.getLength(), c.getExceptionIndexTable(), c.getConstantPool());
  }

  public ExceptionTable(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, (int[]) null, constant_pool);
    int number_of_exceptions = input.readUnsignedShort();
    exception_index_table = new int[number_of_exceptions];
    for (int i = 0; i < number_of_exceptions; i++) {
      exception_index_table[i] = input.readUnsignedShort();
    }
  }

  public ExceptionTable(int name_index, int length, int[] exception_index_table, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_EXCEPTIONS, name_index, length, constant_pool);
    this.exception_index_table = exception_index_table != null ? exception_index_table : new int[0];
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    final StringBuilder buf = new StringBuilder();
    String str;
    for (int i = 0; i < exception_index_table.length; i++) {
      str = super.getConstantPool().getConstantString(exception_index_table[i], TypeConstants.CONSTANT_Class);
      buf.append(Utility.compactClassName(str, false));
      if (i < (exception_index_table.length - 1)) {
        buf.append(", ");
      }
    }
    buf.append("\n");
    out.print(buf.toString());
  }

  public int[] getExceptionIndexTable() {
    return exception_index_table;
  }

  public String[] getExceptionNames() {
    String[] names = new String[exception_index_table.length];
    for (int i = 0; i < exception_index_table.length; i++) {
      names[i] = super.getConstantPool().getConstantString(exception_index_table[i], TypeConstants.CONSTANT_Class).replace('/', '.');
    }
    return names;
  }

  public int getNumberOfExceptions() {
    return exception_index_table == null ? 0 : exception_index_table.length;
  }

  public void setExceptionIndexTable(int[] exception_index_table) {
    this.exception_index_table = exception_index_table != null ? exception_index_table : new int[0];
  }

  @Override
  public final String toString() {
    final StringBuilder buf = new StringBuilder();
    String str;
    buf.append(" Exceptions: ");
    for (int i = 0; i < exception_index_table.length; i++) {
      str = super.getConstantPool().getConstantString(exception_index_table[i], TypeConstants.CONSTANT_Class);
      buf.append(Utility.compactClassName(str, false));
      if (i < (exception_index_table.length - 1)) {
        buf.append(", ");
      }
    }
    buf.append("\n");
    return buf.toString();
  }
}
