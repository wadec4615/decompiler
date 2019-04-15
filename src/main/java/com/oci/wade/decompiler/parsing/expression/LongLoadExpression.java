package com.oci.wade.decompiler.parsing.expression;

import com.oci.wade.decompiler.util.BinaryTree;

public class LongLoadExpression extends Expression {
    private final long value;

    public LongLoadExpression(long value) {
	this.value = value;
    }

    @Override
    public String getStatement(BinaryTree tree) {
	return null;
    }

    public long getValue() {
	return value;
    }
}
