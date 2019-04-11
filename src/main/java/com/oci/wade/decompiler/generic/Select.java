package com.oci.wade.decompiler.generic;

import com.oci.wade.decompiler.constants.Opcode;

public abstract class Select extends BranchInstruction implements VariableLengthInstruction, StackConsumer, StackProducer {
  protected int[] match;
  private int[] jump_table;
  private int padding;

  public Select(Opcode opcode, int low, int high, int offset, int[] jump_table) {
    super(opcode, -1);
    this.jump_table = jump_table;
  }

  public Select(Opcode opcode, int npairs, int offset, int[] match, int[] jump_table, int padding) {
    super(opcode, -1);
    this.match = match;
    this.jump_table = jump_table;
    this.padding = padding;
  }

  public int[] getJump_table() {
    return jump_table;
  }

  public int[] getMatch() {
    return match;
  }

  public int getPadding() {
    return padding;
  }

  public void setJump_table(int[] jump_table) {
    this.jump_table = jump_table;
  }

  public void setMatch(int[] match) {
    this.match = match;
  }

  public void setPadding(int padding) {
    this.padding = padding;
  }
}
