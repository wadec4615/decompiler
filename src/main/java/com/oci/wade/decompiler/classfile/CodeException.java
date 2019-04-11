package com.oci.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import com.oci.wade.decompiler.constants.TypeConstants;
import com.oci.wade.decompiler.util.Utility;

public class CodeException implements Cloneable {
  private int start_pc;
  private int end_pc;
  private int handler_pc;
  private int catch_type;

  public CodeException(CodeException c) {
    this(c.getStartPC(), c.getEndPC(), c.getHandlerPC(), c.getCatchType());
  }

  public CodeException(DataInput file) throws IOException {
    this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort());
  }

  public CodeException(int start_pc, int end_pc, int handler_pc, int catch_type) {
    this.start_pc = start_pc;
    this.end_pc = end_pc;
    this.handler_pc = handler_pc;
    this.catch_type = catch_type;
  }

  public void decompile(PrintStream out, String indent, ConstantPool cp, boolean verbose) {
    String str;
    if (catch_type == 0) {
      str = "<Any exception>(0)";
    } else {
      str = Utility.compactClassName(cp.getConstantString(catch_type, TypeConstants.CONSTANT_Class), false) + (verbose ? "(" + catch_type + ")" : "");
    }
    out.print(start_pc + "\t" + end_pc + "\t" + handler_pc + "\t" + str);
  }

  public int getCatchType() {
    return catch_type;
  }

  public int getEndPC() {
    return end_pc;
  }

  public int getHandlerPC() {
    return handler_pc;
  }

  public int getStartPC() {
    return start_pc;
  }

  public void setCatchType(int catch_type) {
    this.catch_type = catch_type;
  }

  public void setEndPC(int end_pc) {
    this.end_pc = end_pc;
  }

  public void setHandlerPC(int handler_pc) {
    this.handler_pc = handler_pc;
  }

  public void setStartPC(int start_pc) {
    this.start_pc = start_pc;
  }

  @Override
  public String toString() {
    return "CodeException(start_pc = " + start_pc + ", end_pc = " + end_pc + ", handler_pc = " + handler_pc + ", catch_type = " + catch_type + ")";
  }

  public String toString(ConstantPool cp) {
    return toString(cp, true);
  }

  public String toString(ConstantPool cp, boolean verbose) {
    String str;
    if (catch_type == 0) {
      str = "<Any exception>(0)";
    } else {
      str = Utility.compactClassName(cp.getConstantString(catch_type, TypeConstants.CONSTANT_Class), false) + (verbose ? "(" + catch_type + ")" : "");
    }
    return start_pc + "\t" + end_pc + "\t" + handler_pc + "\t" + str;
  }
}
