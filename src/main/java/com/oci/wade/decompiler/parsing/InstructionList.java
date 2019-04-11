package com.oci.wade.decompiler.parsing;

import java.io.PrintStream;
import com.oci.wade.decompiler.attribute.LocalVariableTable;
import com.oci.wade.decompiler.classfile.LocalVariable;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.generic.InvokeInstruction;
import com.oci.wade.decompiler.generic.LoadInstruction;
import com.oci.wade.decompiler.util.Utility;

@SuppressWarnings("unused")
public class InstructionList {
  private final Instruction[] instructions;
  private final LocalVariableTable localVariableTable;
  private final ExpressionStack stack;

  public InstructionList(Instruction[] instructions, LocalVariableTable localVariableTable) {
    this.instructions = instructions;
    this.localVariableTable = localVariableTable;
    stack = new ExpressionStack();
  }

  public void decompile(PrintStream out, String indent, String className, String superClassName) {
    String name, signature;
    LocalVariable variable;
    int index;
    className = className.replace("/", ".");
    for (Instruction instruction : instructions) {
      switch (instruction.getOpcode()) {
        case ALOAD:
        case ALOAD_0:
        case ALOAD_1:
        case ALOAD_2:
        case ALOAD_3:
          index = ((LoadInstruction) instruction).getIndex();
          variable = localVariableTable.getLocalVariable(index);
          name = variable.getName();
          signature = Utility.signatureToString(variable.getSignature(), false);
          if (signature.equals(className)) {
            stack.push(new Expression("super()"));
          }
          break;
        case INVOKESPECIAL:
          InvokeInstruction invoke = (InvokeInstruction) instruction;
          name = invoke.getParameter();
          signature = invoke.getFormat();
          break;
      }
    }
  }
}
