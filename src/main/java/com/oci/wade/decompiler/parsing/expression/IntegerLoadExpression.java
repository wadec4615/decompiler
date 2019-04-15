package com.oci.wade.decompiler.parsing.expression;

import com.oci.wade.decompiler.util.BinaryTree;

public class IntegerLoadExpression extends Expression {
    private final int literal;

    public IntegerLoadExpression(int literal) {
	this.literal = literal;
    }

    public int getLiteral() {
	return literal;
    }

    @Override
    public String getStatement(BinaryTree tree) {
	tree.insert(this);
	return null;
    }
}
