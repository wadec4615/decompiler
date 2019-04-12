package com.oci.wade.decompiler.parsing.expression;

import java.util.Stack;

public class LongReturnExpression extends Expression {
    @Override
    public String getStatement(Stack<Expression> stack) {
	Expression expression1 = stack.lastElement();
	if (expression1 instanceof ExpressionTree) {
	    return "return " + ((ExpressionTree) expression1).getStatement(stack);
	}
	return null;
    }
}
