package com.oci.wade.decompiler.parsing;

import java.util.Stack;

public class ExpressionStack {
  private Stack<Expression> stack;

  public ExpressionStack() {
    stack = new Stack<>();
  }

  public void push(Expression expression) {
    stack.push(expression);
  }
}
