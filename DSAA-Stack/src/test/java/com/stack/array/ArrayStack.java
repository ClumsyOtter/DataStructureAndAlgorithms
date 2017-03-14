package com.stack.array;

import org.junit.Test;

public class ArrayStack {
	@Test
	public void testArrayStack() throws Exception {
		StackByArray stackByArray = new StackByArray();
		Object object = new Integer(10);
		stackByArray.push(object);
		object = new Integer(20);
		stackByArray.push(object);
		stackByArray.deleteStack();
		System.out.println(stackByArray.pop());
	}
}
