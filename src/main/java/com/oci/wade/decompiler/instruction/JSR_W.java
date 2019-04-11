package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.JsrInstruction;

public class JSR_W extends JsrInstruction {
  public JSR_W(int offset) {
    super(Opcode.JSR_W, offset);
    super.setLength(5);
  }
}
