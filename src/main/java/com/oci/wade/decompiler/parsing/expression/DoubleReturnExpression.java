package com.oci.wade.decompiler.parsing.expression;

import com.oci.wade.decompiler.util.BinaryTree;

public class DoubleReturnExpression extends Expression {
    @Override
    public String getStatement(BinaryTree tree) {
	Expression expression = tree.pop().getData();
	if (expression instanceof DoubleLoadExpression) {
	    return "return " + ((DoubleLoadExpression) expression).getValue() + ";";
	}
	return null;
    }
}
