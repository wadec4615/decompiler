package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.classfile.LocalVariable;
import com.oci.wade.decompiler.constants.AttributeConstants;

public class LocalVariableTypeTable extends Attribute {
  private LocalVariable[] local_variable_type_table;

  public LocalVariableTypeTable(int nameIdx, int len, DataInput input, ConstantPool cpool) throws IOException {
    this(nameIdx, len, (LocalVariable[]) null, cpool);
    int local_variable_type_table_length = input.readUnsignedShort();
    local_variable_type_table = new LocalVariable[local_variable_type_table_length];
    for (int i = 0; i < local_variable_type_table_length; i++) {
      local_variable_type_table[i] = new LocalVariable(input, cpool);
    }
  }

  public LocalVariableTypeTable(int name_index, int length, LocalVariable[] local_variable_table, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_LOCAL_VARIABLE_TYPE_TABLE, name_index, length, constant_pool);
    this.local_variable_type_table = local_variable_table;
  }

  public LocalVariableTypeTable(LocalVariableTypeTable c) {
    this(c.getNameIndex(), c.getLength(), c.getLocalVariableTypeTable(), c.getConstantPool());
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    // TODO Auto-generated method stub
  }

  public LocalVariable getLocalVariable(int index) {
    for (LocalVariable variable : local_variable_type_table) {
      if (variable.getIndex() == index) {
        return variable;
      }
    }
    return null;
  }

  public LocalVariable[] getLocalVariableTypeTable() {
    return local_variable_type_table;
  }

  public int getTableLength() {
    return local_variable_type_table == null ? 0 : local_variable_type_table.length;
  }

  public void setLocalVariableTable(LocalVariable[] local_variable_table) {
    this.local_variable_type_table = local_variable_table;
  }
}
