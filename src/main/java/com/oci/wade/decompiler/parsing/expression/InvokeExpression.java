package com.oci.wade.decompiler.parsing.expression;

import java.util.Stack;
import com.oci.wade.decompiler.classfile.JavaClass;

public class InvokeExpression extends Expression {
    private final String signature;
    private final JavaClass caller;

    public InvokeExpression(String signature, JavaClass caller) {
	this.signature = signature;
	this.caller = caller;
    }

    @Override
    public String getStatement(Stack<Expression> stack) {
	ClassExpression clazz = (ClassExpression) stack.pop();
	int position = signature.lastIndexOf(":");
	String methodSignature = signature.substring(0, position);
	String format = signature.substring(position + 1);
	if (methodSignature.contains("<init>") && methodSignature.contains(clazz.getClazz().getSuperclassName())) {
	    return "super();";
	}
	return null;
    }
}
