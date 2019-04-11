package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.Select;

public class LOOKUPSWITCH extends Select {
  public LOOKUPSWITCH(int npairs, int offset, int[] match, int[] jump_table, int no_pad_bytes) {
    super(Opcode.LOOKUPSWITCH, npairs, offset, match, jump_table, no_pad_bytes);
  }
}
