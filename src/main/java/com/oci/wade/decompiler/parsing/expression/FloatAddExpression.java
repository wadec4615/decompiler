package com.oci.wade.decompiler.parsing.expression;

import com.oci.wade.decompiler.util.BinaryTree;

public class FloatAddExpression extends Expression {
    @Override
    public String getStatement(BinaryTree tree) {
	tree.insert(BinaryTree.Operator.Plus);
	return null;
    }
}
