package com.stack.array;

//利用动态数组来实现动态栈
public class StackByDynamicArray {
	// 栈的大小
	private int stackSize;
	// 栈顶
	private int top;
	// 栈的容器类型
	private Object[] array;

	// 初始化栈 栈大小默认为10
	public StackByDynamicArray() {
		stackSize = 10;
		array = new Object[stackSize];
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

	// 若栈满则扩充栈，每次扩充是当前栈的一倍
	public void doubleStack() {

		stackSize = stackSize * 2;
		// 创建新栈
		Object[] doubleArray = new Object[stackSize];
		// 将旧栈复制到新栈
		System.arraycopy(array, 0, doubleArray, 0, array.length);
		// 新栈替代旧栈
		array = doubleArray;
	}

	// 压栈
	public void push(Object obj) {
		if (isFull()) {
			doubleStack();
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
