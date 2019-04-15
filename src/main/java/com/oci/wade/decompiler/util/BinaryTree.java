package com.oci.wade.decompiler.util;

import com.oci.wade.decompiler.parsing.expression.Expression;

public class BinaryTree {
    public enum Operator {
	Plus, Minus, times, divide;
    }
    public class StackNode {
	TreeNode treeNode;
	StackNode next;

	public StackNode(TreeNode treeNode) {
	    this.treeNode = treeNode;
	    next = null;
	}
    }
    public class TreeNode {
	Expression data;
	Operator operator;
	TreeNode left, right;

	public TreeNode(Expression data, Operator op) {
	    this.data = data;
	    this.operator = op;
	    this.left = null;
	    this.right = null;
	}
    }
    private StackNode top;

    public BinaryTree() {
	clear();
    }

    public void clear() {
	top = null;
    }

    private void insert(Expression val) {
	try {
	    TreeNode nptr = new TreeNode(val, null);
	    push(nptr);
	} catch (Exception e) {
	    System.out.println("Invalid Expression");
	}
    }

    private void insert(Operator op) {
	try {
	    TreeNode nptr = new TreeNode(null, op);
	    nptr.left = pop();
	    nptr.right = pop();
	    push(nptr);
	} catch (Exception e) {
	    System.out.println("Invalid Expression");
	}
    }

    private TreeNode peek() {
	return top.treeNode;
    }

    private TreeNode pop() {
	if (top == null) {
	    throw new RuntimeException("Underflow");
	} else {
	    TreeNode ptr = top.treeNode;
	    top = top.next;
	    return ptr;
	}
    }

    public void postfix() {
	postOrder(peek());
    }

    private void postOrder(TreeNode ptr) {
	if (ptr != null) {
	    postOrder(ptr.left);
	    postOrder(ptr.right);
	    System.out.print(ptr.data);
	}
    }

    public void prefix() {
	preOrder(peek());
    }

    private void preOrder(TreeNode ptr) {
	if (ptr != null) {
	    System.out.print(ptr.data);
	    preOrder(ptr.left);
	    preOrder(ptr.right);
	}
    }

    private void push(TreeNode ptr) {
	if (top == null) {
	    top = new StackNode(ptr);
	} else {
	    StackNode nptr = new StackNode(ptr);
	    nptr.next = top;
	    top = nptr;
	}
    }
}
