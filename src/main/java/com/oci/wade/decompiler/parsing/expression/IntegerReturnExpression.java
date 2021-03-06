package com.oci.wade.decompiler.parsing.expression;

import com.oci.wade.decompiler.util.BinaryTree;

public class IntegerReturnExpression extends Expression {
    @Override
    public String getStatement(BinaryTree tree) {
	Expression expression = tree.pop().getData();
	if (expression instanceof IntegerLoadExpression) {
	    return "return " + ((IntegerLoadExpression) expression).getLiteral() + ";";
	}
	return null;
    }
}
