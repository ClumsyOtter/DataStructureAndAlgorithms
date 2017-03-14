package com.stack.linkedlist;

public class StackNode {
	// 指向下一个节点
	private StackNode next;
	// 储存的内容
	private Object data;

	public StackNode getNext() {
		return next;
	}

	public void setNext(StackNode next) {
		this.next = next;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
