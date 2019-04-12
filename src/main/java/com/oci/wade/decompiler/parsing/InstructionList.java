package com.oci.wade.decompiler.parsing;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Stack;
import com.oci.wade.decompiler.attribute.LocalVariableTable;
import com.oci.wade.decompiler.classfile.ConstantPool;
import com.oci.wade.decompiler.classfile.JavaClass;
import com.oci.wade.decompiler.classfile.LocalVariable;
import com.oci.wade.decompiler.constants.TypeConstants;
import com.oci.wade.decompiler.generic.Instruction;
import com.oci.wade.decompiler.generic.InvokeInstruction;
import com.oci.wade.decompiler.generic.LoadInstruction;
import com.oci.wade.decompiler.instruction.ICONST;
import com.oci.wade.decompiler.instruction.LDC2_W;
import com.oci.wade.decompiler.parsing.expression.ClassLoadExpression;
import com.oci.wade.decompiler.parsing.expression.DoubleReturnExpression;
import com.oci.wade.decompiler.parsing.expression.Expression;
import com.oci.wade.decompiler.parsing.expression.IntegerLoadExpression;
import com.oci.wade.decompiler.parsing.expression.IntegerReturnExpression;
import com.oci.wade.decompiler.parsing.expression.InvokeExpression;
import com.oci.wade.decompiler.parsing.expression.LongAddExpression;
import com.oci.wade.decompiler.parsing.expression.LongLoadExpression;
import com.oci.wade.decompiler.parsing.expression.LongReturnExpression;
import com.oci.wade.decompiler.util.RepositoryImpl;
import com.oci.wade.decompiler.util.Utility;

public class InstructionList {
    private final Instruction[] instructions;
    private final LocalVariableTable localVariableTable;
    private final LinkedList<Expression> list;
    private final ConstantPool constantPool;

    public InstructionList(Instruction[] instructions, ConstantPool constantPool, LocalVariableTable localVariableTable) {
	this.instructions = instructions;
	this.localVariableTable = localVariableTable;
	this.constantPool = constantPool;
	this.list = new LinkedList<Expression>();
    }

    public void convertToExpressionList(String className, String superClassName) throws ClassNotFoundException {
	String name, signature, value;
	LocalVariable variable;
	long index;
	JavaClass clazz;
	JavaClass superClazz;
	LoadInstruction load;
	className = className.replace("/", ".");
	for (Instruction instruction : instructions) {
	    switch (instruction.getOpcode()) {
		case ALOAD:
		case ALOAD_0:
		case ALOAD_1:
		case ALOAD_2:
		case ALOAD_3:
		    load = (LoadInstruction) instruction;
		    index = load.getIndex();
		    variable = localVariableTable.getLocalVariable((int) index);
		    name = variable.getName();
		    signature = Utility.signatureToString(variable.getSignature(), false);
		    clazz = RepositoryImpl.lookupClass(signature);
		    superClazz = RepositoryImpl.lookupClass(superClassName);
		    list.add(new ClassLoadExpression(name, signature, clazz, superClazz));
		    break;
		case DLOAD:
		case DLOAD_0:
		case DLOAD_1:
		case DLOAD_2:
		case DLOAD_3:
		    load = (LoadInstruction) instruction;
		    index = load.getIndex();
		    variable = localVariableTable.getLocalVariable((int) index);
		    name = variable.getName();
		    signature = Utility.signatureToString(variable.getSignature(), false);
		    list.add(new ClassLoadExpression(name, signature, null, null));
		    break;
		case LLOAD:
		case LLOAD_0:
		case LLOAD_1:
		case LLOAD_2:
		case LLOAD_3:
		    load = (LoadInstruction) instruction;
		    index = load.getIndex();
		    variable = localVariableTable.getLocalVariable((int) index);
		    name = variable.getName();
		    signature = Utility.signatureToString(variable.getSignature(), false);
		    list.add(new ClassLoadExpression(name, signature, null, null));
		    break;
		case LDC2_W:
		    LDC2_W ldc2w = (LDC2_W) instruction;
		    index = constantPool.constantToLong(ldc2w.getIndex(), TypeConstants.CONSTANT_Long);
		    list.add(new LongLoadExpression(index));
		    break;
		case ICONST_0:
		case ICONST_1:
		case ICONST_2:
		case ICONST_3:
		case ICONST_4:
		case ICONST_5:
		    ICONST iconst = (ICONST) instruction;
		    list.add(new IntegerLoadExpression(iconst.getValue().intValue()));
		    break;
		case IRETURN:
		    list.add(new IntegerReturnExpression());
		    break;
		case DRETURN:
		    list.add(new DoubleReturnExpression());
		    break;
		case LRETURN:
		    list.add(new LongReturnExpression());
		    break;
		case INVOKESPECIAL:
		    InvokeInstruction invoke = (InvokeInstruction) instruction;
		    index = invoke.getIndex();
		    signature = constantPool.constantToString((int) index, TypeConstants.CONSTANT_Methodref);
		    clazz = RepositoryImpl.lookupClass(superClassName);
		    list.add(new InvokeExpression(signature, clazz));
		    break;
		case LADD:
		    list.add(new LongAddExpression());
		    break;
	    }
	}
	System.out.println();
    }

    public void decompile(PrintStream out, String indent) {
	Stack<Expression> stack = new Stack<>();
	for (Expression expression : list) {
	    String result = expression.getStatement(stack);
	    if (result != null) {
		out.println(indent + " " + result);
	    }
	}
    }
}
