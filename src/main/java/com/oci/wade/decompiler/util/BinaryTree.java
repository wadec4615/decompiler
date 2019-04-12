package com.oci.wade.decompiler.util;

public class BinaryTree {
    class StackNode {
	TreeNode treeNode;
	StackNode next;

	public StackNode(TreeNode treeNode) {
	    this.treeNode = treeNode;
	    next = null;
	}
    }
    class TreeNode {
	char data;
	TreeNode left, right;

	public TreeNode(char data) {
	    this.data = data;
	    this.left = null;
	    this.right = null;
	}
    }
    private static StackNode root;

    public BinaryTree() {
	root = null;
    }

    public void buildTree(String eqn) {
	for (int i = eqn.length() - 1; i >= 0; i--) {
	    insert(eqn.charAt(i));
	}
    }

    public void clear() {
	root = null;
    }

    public double evaluate() {
	return evaluate(peek());
    }

    public double evaluate(TreeNode ptr) {
	if ((ptr.left == null) && (ptr.right == null)) {
	    return toDigit(ptr.data);
	} else {
	    double result = 0.0;
	    double left = evaluate(ptr.left);
	    double right = evaluate(ptr.right);
	    char operator = ptr.data;
	    switch (operator) {
		case '+':
		    result = left + right;
		    break;
		case '-':
		    result = left - right;
		    break;
		case '*':
		    result = left * right;
		    break;
		case '/':
		    result = left / right;
		    break;
		default:
		    result = left + right;
		    break;
	    }
	    return result;
	}
    }

    public void infix() {
	inOrder(peek());
    }

    private void inOrder(TreeNode ptr) {
	if (ptr != null) {
	    inOrder(ptr.left);
	    System.out.print(ptr.data);
	    inOrder(ptr.right);
	}
    }

    private void insert(char val) {
	try {
	    if (isDigit(val)) {
		TreeNode nptr = new TreeNode(val);
		push(nptr);
	    } else if (isOperator(val)) {
		TreeNode nptr = new TreeNode(val);
		nptr.left = pop();
		nptr.right = pop();
		push(nptr);
	    }
	} catch (Exception e) {
	    System.out.println("Invalid Expression");
	}
    }

    private boolean isDigit(char ch) {
	return (ch >= '0') && (ch <= '9');
    }

    private boolean isOperator(char ch) {
	return (ch == '+') || (ch == '-') || (ch == '*') || (ch == '/');
    }

    private TreeNode peek() {
	return root.treeNode;
    }

    private TreeNode pop() {
	if (root == null) {
	    throw new RuntimeException("Underflow");
	} else {
	    TreeNode ptr = root.treeNode;
	    root = root.next;
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
	if (root == null) {
	    root = new StackNode(ptr);
	} else {
	    StackNode nptr = new StackNode(ptr);
	    nptr.next = root;
	    root = nptr;
	}
    }

    private int toDigit(char ch) {
	return ch - '0';
    }
}