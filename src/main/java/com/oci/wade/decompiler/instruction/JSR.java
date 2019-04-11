package com.oci.wade.decompiler.instruction;

import com.oci.wade.decompiler.constants.Opcode;
import com.oci.wade.decompiler.generic.JsrInstruction;
import com.oci.wade.decompiler.generic.VariableLengthInstruction;

public class JSR extends JsrInstruction implements VariableLengthInstruction {
  public JSR(int offset) {
    super(Opcode.JSR, offset);
  }
}
