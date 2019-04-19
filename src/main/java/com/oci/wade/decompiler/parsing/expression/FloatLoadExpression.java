package com.oci.wade.decompiler.parsing.expression;

import com.oci.wade.decompiler.util.BinaryTree;

public class FloatLoadExpression extends Expression {
    private final float value;

    public FloatLoadExpression(float value) {
	this.value = value;
    }

    @Override
    public String getStatement(BinaryTree tree) {
	return null;
    }

    public float getValue() {
	return value;
    }
}
