package com.oci.wade.decompiler.parsing.expression;

import com.oci.wade.decompiler.util.BinaryTree;

public class DoubleLoadExpression extends Expression {
    private final double value;

    public DoubleLoadExpression(double value) {
	this.value = value;
    }

    @Override
    public String getStatement(BinaryTree tree) {
	return null;
    }

    public double getValue() {
	return value;
    }
}
