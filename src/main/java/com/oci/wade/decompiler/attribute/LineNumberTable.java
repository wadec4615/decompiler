package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.classfile.LineNumber;
import com.oci.wade.decompiler.constants.AttributeConstants;

public class LineNumberTable extends Attribute {
  private static int MAX_LINE_LENGTH = 72;
  private LineNumber[] line_number_table;

  public LineNumberTable(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, (LineNumber[]) null, constant_pool);
    int line_number_table_length = input.readUnsignedShort();
    line_number_table = new LineNumber[line_number_table_length];
    for (int i = 0; i < line_number_table_length; i++) {
      line_number_table[i] = new LineNumber(input);
    }
  }

  public LineNumberTable(int name_index, int length, LineNumber[] line_number_table, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_LINE_NUMBER_TABLE, name_index, length, constant_pool);
    this.line_number_table = line_number_table;
  }

  public LineNumberTable(LineNumberTable c) {
    this(c.getNameIndex(), c.getLength(), c.getLineNumberTable(), c.getConstantPool());
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public LineNumber[] getLineNumberTable() {
    return line_number_table;
  }

  public int getSourceLine(int pos) {
    int l = 0;
    int r = line_number_table.length - 1;
    if (r < 0) {
      return -1;
    }
    int min_index = -1;
    int min = -1;
    do {
      int i = (l + r) / 2;
      int j = line_number_table[i].getStartPC();
      if (j == pos) {
        return line_number_table[i].getLineNumber();
      } else if (pos < j) {
        r = i - 1;
      } else {
        l = i + 1;
      }
      if ((j < pos) && (j > min)) {
        min = j;
        min_index = i;
      }
    } while (l <= r);
    if (min_index < 0) {
      return -1;
    }
    return line_number_table[min_index].getLineNumber();
  }

  public int getTableLength() {
    return line_number_table == null ? 0 : line_number_table.length;
  }

  public void setLineNumberTable(LineNumber[] line_number_table) {
    this.line_number_table = line_number_table;
  }

  @Override
  public final String toString() {
    final StringBuilder buf = new StringBuilder();
    final StringBuilder line = new StringBuilder();
    final String newLine = System.getProperty("line.separator", "\n");
    buf.append("  ");
    for (int i = 0; i < line_number_table.length; i++) {
      line.append(line_number_table[i].toString());
      if (i < (line_number_table.length - 1)) {
        line.append(",\n  ");
      }
      if ((line.length() > MAX_LINE_LENGTH) && (i < (line_number_table.length - 1))) {
        line.append(newLine);
        buf.append(line);
        line.setLength(0);
      }
    }
    buf.append(line);
    return buf.toString();
  }
}
