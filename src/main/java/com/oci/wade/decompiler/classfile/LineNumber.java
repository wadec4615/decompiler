package com.oci.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.IOException;

public class LineNumber implements Cloneable {
  private short start_pc;
  private short line_number;

  public LineNumber(DataInput file) throws IOException {
    this(file.readUnsignedShort(), file.readUnsignedShort());
  }

  public LineNumber(int start_pc, int line_number) {
    this.start_pc = (short) start_pc;
    this.line_number = (short) line_number;
  }

  public LineNumber(LineNumber c) {
    this(c.getStartPC(), c.getLineNumber());
  }

  public LineNumber copy() {
    try {
      return (LineNumber) clone();
    } catch (CloneNotSupportedException e) {
    }
    return null;
  }

  public int getLineNumber() {
    return 0xffff & line_number;
  }

  public int getStartPC() {
    return 0xffff & start_pc;
  }

  public void setLineNumber(int line_number) {
    this.line_number = (short) line_number;
  }

  public void setStartPC(int start_pc) {
    this.start_pc = (short) start_pc;
  }

  @Override
  public String toString() {
    return "LineNumber(" + start_pc + ", " + line_number + ")";
  }
}
