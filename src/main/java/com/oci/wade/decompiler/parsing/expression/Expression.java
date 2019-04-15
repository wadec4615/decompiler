package com.oci.wade.decompiler.parsing.expression;

import com.oci.wade.decompiler.util.BinaryTree;

public abstract class Expression {
    public abstract String getStatement(BinaryTree tree);
}
