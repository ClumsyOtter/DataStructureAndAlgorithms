package com.tree.AVLtree;

public class AvlNode<T> {
	public AvlNode(T date) {
		this(date, null, null);
	}

	public AvlNode(T date, AvlNode<T> left, AvlNode<T> right) {
		super();
		this.date = date;
		this.left = left;
		this.right = right;
		this.hight = 0;
	}

	T date;
	AvlNode<T> left;
	AvlNode<T> right;
	int hight; // 该节点的高度
}
