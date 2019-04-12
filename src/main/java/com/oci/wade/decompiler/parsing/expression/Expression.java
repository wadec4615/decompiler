package com.oci.wade.decompiler.parsing.expression;

import java.util.Stack;

public abstract class Expression {
    public abstract String getStatement(Stack<Expression> stack);
}
