package com.oci.wade.decompiler.parsing.expression;

import com.oci.wade.decompiler.classfile.JavaClass;
import com.oci.wade.decompiler.util.BinaryTree;

public class InvokeExpression extends Expression {
    private final String signature;
    private final JavaClass caller;

    public InvokeExpression(String signature, JavaClass caller) {
	this.signature = signature;
	this.caller = caller;
    }

    @Override
    public String getStatement(BinaryTree tree) {
	ClassExpression clazz = (ClassExpression) tree.pop().getData();
	int position = signature.lastIndexOf(":");
	String methodSignature = signature.substring(0, position);
	String format = signature.substring(position + 1);
	if (methodSignature.contains("<init>") && methodSignature.contains(clazz.getClazz().getSuperclassName())) {
	    return "super();";
	}
	return null;
    }
}
