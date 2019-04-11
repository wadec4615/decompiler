package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.classfile.LocalVariable;
import com.oci.wade.decompiler.constants.AttributeConstants;

public class LocalVariableTable extends Attribute {
  private LocalVariable[] local_variable_table;

  public LocalVariableTable(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
    this(name_index, length, (LocalVariable[]) null, constant_pool);
    int local_variable_table_length = input.readUnsignedShort();
    local_variable_table = new LocalVariable[local_variable_table_length];
    for (int i = 0; i < local_variable_table_length; i++) {
      local_variable_table[i] = new LocalVariable(input, constant_pool);
    }
  }

  public LocalVariableTable(int name_index, int length, LocalVariable[] local_variable_table, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_LOCAL_VARIABLE_TABLE, name_index, length, constant_pool);
    this.local_variable_table = local_variable_table;
  }

  public LocalVariableTable(LocalVariableTable c) {
    this(c.getNameIndex(), c.getLength(), c.getLocalVariableTable(), c.getConstantPool());
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public LocalVariable getLocalVariable(int index) {
    for (LocalVariable variable : local_variable_table) {
      if (variable.getIndex() == index) {
        return variable;
      }
    }
    return null;
  }

  public LocalVariable getLocalVariable(int index, int pc) {
    for (LocalVariable variable : local_variable_table) {
      if (variable.getIndex() == index) {
        int start_pc = variable.getStartPC();
        int end_pc = start_pc + variable.getLength();
        if ((pc >= start_pc) && (pc <= end_pc)) {
          return variable;
        }
      }
    }
    return null;
  }

  public LocalVariable[] getLocalVariableTable() {
    return local_variable_table;
  }

  public int getTableLength() {
    return local_variable_table == null ? 0 : local_variable_table.length;
  }

  public void setLocalVariableTable(LocalVariable[] local_variable_table) {
    this.local_variable_table = local_variable_table;
  }

  @Override
  public final String toString() {
    final StringBuilder buf = new StringBuilder();
    for (int i = 0; i < local_variable_table.length; i++) {
      buf.append("  ");
      buf.append(local_variable_table[i]);
      if (i < (local_variable_table.length - 1)) {
        buf.append('\n');
      }
    }
    return buf.toString();
  }
}
