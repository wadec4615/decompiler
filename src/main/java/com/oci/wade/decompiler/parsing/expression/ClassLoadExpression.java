package com.oci.wade.decompiler.parsing.expression;

import java.util.Stack;
import com.oci.wade.decompiler.classfile.JavaClass;

public class ClassLoadExpression extends Expression {
    private final String signature;
    private final JavaClass caller;
    private final JavaClass callee;
    private final String name;

    public ClassLoadExpression(String name, String signature, JavaClass caller, JavaClass callee) {
	this.name = name;
	this.signature = signature;
	this.caller = caller;
	this.callee = callee;
    }

    public JavaClass getCallee() {
	return callee;
    }

    public JavaClass getCaller() {
	return caller;
    }

    public String getName() {
	return name;
    }

    public String getSignature() {
	return signature;
    }

    @Override
    public String getStatement(Stack<Expression> stack) {
	stack.push(new ClassExpression(name, signature, caller));
	return null;
    }
}
