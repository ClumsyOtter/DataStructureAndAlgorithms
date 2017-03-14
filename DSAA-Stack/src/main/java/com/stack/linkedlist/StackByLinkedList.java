package com.stack.linkedlist;

public class StackByLinkedList {
	private StackNode headNode;

	// 压栈
	public void push(Object data) {
		StackNode stackNode = new StackNode();
		stackNode.setData(data);
		if (null == headNode) {
			headNode = stackNode;
		} else {
			stackNode.setNext(headNode);
			headNode = stackNode;
		}
	}

	// 返回栈顶的数据
	public Object top() {
		if (headNode == null)
			return null;
		else
			return headNode.getData();
	}

	// 出栈
	public Object pop() throws Exception {
		if (headNode == null)
			throw new Exception("空栈");
		else {
			Object object = headNode.getData();
			headNode = headNode.getNext();
			return object;
		}
	}

	// 是否为空
	public boolean isEmpty() {
		return (null == headNode);
	}

	// 删除栈
	public void deleteStack() {
		headNode = null;
	}
}
