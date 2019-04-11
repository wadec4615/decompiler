package com.oci.wade.decompiler.attribute;

import java.io.DataInput;
import java.io.PrintStream;
import com.oci.wade.decompiler.classfile.CodeException;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.constants.AttributeConstants;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.util.Utility;

public class Code extends Attribute {
  private int max_stack;
  private int max_locals;
  private byte[] code;
  private CodeException[] exception_table;
  private Attribute[] attributes;
  private Instruction[] instructions;

  public Code(Code c) {
    this(c.getNameIndex(), c.getLength(), c.getMaxStack(), c.getMaxLocals(), c.getCode(), c.getExceptionTable(), c.getAttributes(), c.getConstantPool());
  }

  public Code(int name_index, int length, DataInput file, ConstantPool constant_pool) throws Exception {
    this(name_index, length, file.readUnsignedShort(), file.readUnsignedShort(), (byte[]) null, (CodeException[]) null, (Attribute[]) null, constant_pool);
    int code_length = file.readInt();
    code = new byte[code_length];
    file.readFully(code);
    int exception_table_length = file.readUnsignedShort();
    exception_table = new CodeException[exception_table_length];
    for (int i = 0; i < exception_table_length; i++) {
      exception_table[i] = new CodeException(file);
    }
    int attributes_count = file.readUnsignedShort();
    attributes = new Attribute[attributes_count];
    for (int i = 0; i < attributes_count; i++) {
      attributes[i] = Attribute.readAttribute(file, constant_pool);
    }
    super.setLength(length);
    instructions = Utility.codeToInstructions(code, constant_pool, code.length);
  }

  public Code(int name_index, int length, int max_stack, int max_locals, byte[] code, CodeException[] exception_table, Attribute[] attributes, ConstantPool constant_pool) {
    super(AttributeConstants.ATTR_CODE, name_index, length, constant_pool);
    this.max_stack = max_stack;
    this.max_locals = max_locals;
    this.code = code != null ? code : new byte[0];
    this.exception_table = exception_table != null ? exception_table : new CodeException[0];
    this.attributes = attributes != null ? attributes : new Attribute[0];
    super.setLength(calculateLength());
  }

  @Override
  public void decompile(PrintStream out, String indent) {
    out.print(indent + " Code(max_stack = " + max_stack);
    out.print(", max_locals = " + max_locals);
    out.print(", code_length = " + code.length + ")\n");
    out.print(Utility.codeToString(code, super.getConstantPool(), 0, -1, true, indent + " "));
    if (exception_table.length > 0) {
      out.print("\nException handler(s) = \nFrom\tTo\tHandler\tType\n");
      for (CodeException exception : exception_table) {
        exception.decompile(out, indent, getConstantPool(), true);
      }
    }
  }

  public Attribute[] getAttributes() {
    return attributes;
  }

  public byte[] getCode() {
    return code;
  }

  public CodeException[] getExceptionTable() {
    return exception_table;
  }

  public Instruction[] getInstructions() {
    return instructions;
  }

  public LineNumberTable getLineNumberTable() {
    for (Attribute attribute : attributes) {
      if (attribute instanceof LineNumberTable) {
        return (LineNumberTable) attribute;
      }
    }
    return null;
  }

  public LocalVariableTable getLocalVariableTable() {
    for (Attribute attribute : attributes) {
      if (attribute instanceof LocalVariableTable) {
        return (LocalVariableTable) attribute;
      }
    }
    return null;
  }

  public int getMaxLocals() {
    return max_locals;
  }

  public int getMaxStack() {
    return max_stack;
  }

  public void setAttributes(Attribute[] attributes) {
    this.attributes = attributes != null ? attributes : new Attribute[0];
    super.setLength(calculateLength());
  }

  public void setCode(byte[] code) {
    this.code = code != null ? code : new byte[0];
    super.setLength(calculateLength());
  }

  public void setExceptionTable(CodeException[] exception_table) {
    this.exception_table = exception_table != null ? exception_table : new CodeException[0];
    super.setLength(calculateLength());
  }

  public void setMaxLocals(int max_locals) {
    this.max_locals = max_locals;
  }

  public void setMaxStack(int max_stack) {
    this.max_stack = max_stack;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder();
    buf.append(" Code(max_stack = ").append(max_stack).append(", max_locals = ").append(max_locals).append(", code_length = ").append(code.length).append(")\n").append(Utility.codeToString(code, super.getConstantPool(), 0, -1, true, "  "));
    if (exception_table.length > 0) {
      buf.append("\nException handler(s) = \n").append("From\tTo\tHandler\tType\n");
      for (CodeException exception : exception_table) {
        buf.append(exception.toString(super.getConstantPool(), true)).append("\n");
      }
    }
    if (attributes.length > 0) {
      buf.append("\n Attribute(s) = ").append("\n");
      for (Attribute attribute : attributes) {
        buf.append(attribute).append("\n");
      }
    }
    return buf.toString();
  }

  private int calculateLength() {
    int len = 0;
    if (attributes != null) {
      for (Attribute attribute : attributes) {
        len += attribute.getLength() + 6;
      }
    }
    return len + getInternalLength();
  }

  private int getInternalLength() {
    return 2 + 2 + 4 + code.length + 2 + (8 * (exception_table == null ? 0 : exception_table.length)) + 2;
  }
}
