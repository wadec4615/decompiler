package com.oci.wade.decompiler.parsing.expression;

import com.oci.wade.decompiler.util.BinaryTree;

public class FloatReturnExpression extends Expression {
    @Override
    public String getStatement(BinaryTree tree) {
	Expression expression = tree.pop().getData();
	if (expression instanceof FloatLoadExpression) {
	    return "return " + ((FloatLoadExpression) expression).getValue() + ";";
	}
	return null;
    }
}
