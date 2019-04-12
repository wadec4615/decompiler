package com.oci.wade.decompiler.parsing.expression;

import java.util.Stack;

public class LongLoadExpression extends Expression {
    private final long value;

    public LongLoadExpression(long value) {
	this.value = value;
    }

    @Override
    public String getStatement(Stack<Expression> stack) {
	return null;
    }

    public long getValue() {
	return value;
    }
}
