package com.oci.wade.decompiler.parsing.expression;

import java.util.Stack;

public class DoubleReturnExpression extends Expression {
    @Override
    public String getStatement(Stack<Expression> stack) {
	Expression expression = stack.pop();
	if (expression instanceof ClassExpression) {
	    return "return " + ((ClassExpression) expression).getName() + ";";
	}
	return null;
    }
}
