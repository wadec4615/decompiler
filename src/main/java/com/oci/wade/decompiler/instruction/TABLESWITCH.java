package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.Select;

public class TABLESWITCH extends Select {
  public TABLESWITCH(int low, int high, int offset, int[] jump_table) {
    super(Opcode.TABLESWITCH, low, high, offset, jump_table);
  }
}
