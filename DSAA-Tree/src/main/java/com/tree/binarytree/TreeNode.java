package com.tree.binarytree;

//树的节点
public class TreeNode<T> {
	private T data;
	public TreeNode<T> leftNode;
	public TreeNode<T> rightNode;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
