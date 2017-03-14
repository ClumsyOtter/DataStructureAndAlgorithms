package com.stack.array;

//利用简单的数组实现栈
public class StackByArray {
	// 栈的大小
	private int stackSize;
	// 栈顶
	private int top;
	// 栈的容器类型
	private Object[] array;

	// 初始化栈 栈大小默认为10
	public StackByArray() {
		stackSize = 10;
		array = new Object[stackSize];
		top = -1;
	}

	// 自定义栈大小
	public StackByArray(int stacksize) {
		array = new Object[stacksize];
		top = -1;
	}

	// 判断栈是否为空
	public boolean isEmpty() {
		return (top == -1);
	}

	// 判断栈是否满了
	public boolean isFull() {
		return (top == array.length);
	}

	// 压栈
	public void push(Object obj) throws Exception {
		if (isFull()) {
			throw new Exception("栈满");
		} else
			array[++top] = obj;
	}

	// 出栈
	public Object pop() throws Exception {
		if (isEmpty()) {
			throw new Exception("空栈");
		} else
			return array[top--];
	}

	// 删栈
	public void deleteStack() {
		top = -1;
	}
}
