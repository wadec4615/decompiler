package com.oci.wade.decompiler.parsing.expression;

import java.util.Stack;

public class LongAddExpression extends Expression {
    @Override
    public String getStatement(Stack<Expression> stack) {
	long value = 0;
	String str = "";
	Expression expression1 = stack.pop();
	Expression expression2 = stack.pop();
	if (expression1 instanceof LongLoadExpression) {
	    value = ((LongLoadExpression) expression1).getValue();
	}
	if (expression2 instanceof ClassLoadExpression) {
	    str = ((ClassLoadExpression) expression1).getName();
	}
	stack.push(new ExpressionTree(str, value));
	return null;
    }
}
