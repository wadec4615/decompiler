package com.oci.wade.decompiler.parsing.expression;

import java.util.Stack;

public class IntegerLoadExpression extends Expression {
    private final int literal;

    public IntegerLoadExpression(int literal) {
	this.literal = literal;
    }

    public int getLiteral() {
	return literal;
    }

    @Override
    public String getStatement(Stack<Expression> stack) {
	stack.push(this);
	return null;
    }
}
