package com.oci.wade.decompiler.util;

import com.oci.wade.decompiler.parsing.expression.Expression;

public class BinaryTree {
    public enum Operator {
	Plus, Minus, times, divide;
    }
    public class StackNode {
	private TreeNode treeNode;
	private StackNode next;

	public StackNode(TreeNode treeNode) {
	    this.treeNode = treeNode;
	    next = null;
	}

	public StackNode getNext() {
	    return next;
	}

	public TreeNode getTreeNode() {
	    return treeNode;
	}

	public void setNext(StackNode next) {
	    this.next = next;
	}

	public void setTreeNode(TreeNode treeNode) {
	    this.treeNode = treeNode;
	}
    }
    public class TreeNode {
	private Expression data;
	private Operator operator;
	private TreeNode left, right;

	public TreeNode(Expression data) {
	    this.data = data;
	}

	public TreeNode(TreeNode left, Operator operator, TreeNode right) {
	    this.operator = operator;
	    this.left = left;
	    this.right = right;
	}

	public Expression getData() {
	    return data;
	}

	public TreeNode getLeft() {
	    return left;
	}

	public Operator getOperator() {
	    return operator;
	}

	public TreeNode getRight() {
	    return right;
	}

	public void setData(Expression data) {
	    this.data = data;
	}

	public void setLeft(TreeNode left) {
	    this.left = left;
	}

	public void setOperator(Operator operator) {
	    this.operator = operator;
	}

	public void setRight(TreeNode right) {
	    this.right = right;
	}
    }
    private StackNode top;

    public BinaryTree() {
	clear();
    }

    public void clear() {
	top = null;
    }

    public void insert(Expression val) {
	try {
	    push(new TreeNode(val));
	} catch (Exception e) {
	    System.out.println("Invalid Expression");
	}
    }

    public void insert(Operator op) {
	try {
	    TreeNode left = pop();
	    TreeNode right = pop();
	    push(new TreeNode(left, op, right));
	} catch (Exception e) {
	    System.out.println("Invalid Expression");
	}
    }

    public TreeNode peek() {
	return top.treeNode;
    }

    public TreeNode pop() {
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

    public void preOrder(TreeNode ptr) {
	if (ptr != null) {
	    System.out.print(ptr.data);
	    preOrder(ptr.left);
	    preOrder(ptr.right);
	}
    }

    public void push(TreeNode ptr) {
	if (top == null) {
	    top = new StackNode(ptr);
	} else {
	    StackNode nptr = new StackNode(ptr);
	    nptr.next = top;
	    top = nptr;
	}
    }
}
