package com.oci.wade.decompiler.parsing.expression;

import com.oci.wade.decompiler.classfile.JavaClass;
import com.oci.wade.decompiler.util.BinaryTree;

public class ClassExpression extends Expression {
    private JavaClass clazz;
    private String name;
    private String signature;

    public ClassExpression(String name, String signature, JavaClass clazz) {
	this.name = name;
	this.signature = signature;
	this.clazz = clazz;
    }

    public JavaClass getClazz() {
	return clazz;
    }

    public String getName() {
	return name;
    }

    public String getSignature() {
	return signature;
    }

    @Override
    public String getStatement(BinaryTree tree) {
	return null;
    }
}
