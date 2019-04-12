package com.oci.wade.decompiler.parsing.expression;

import java.util.Stack;

public class IntegerReturnExpression extends Expression {
    @Override
    public String getStatement(Stack<Expression> stack) {
	Expression expression = stack.pop();
	if (expression instanceof IntegerLoadExpression) {
	    return "return " + ((IntegerLoadExpression) expression).getLiteral() + ";";
	}
	return null;
    }
}
