package com.stack.array;

import org.junit.Test;

import com.stack.linkedlist.StackByLinkedList;

public class LinkedListStack {
	@Test
	public void test() throws Exception {
		StackByLinkedList stackByLinkedList = new StackByLinkedList();
		stackByLinkedList.push(new Integer(10));
		stackByLinkedList.push(new Integer(20));
		stackByLinkedList.push(new Integer(30));
		System.out.println(stackByLinkedList.pop());
		System.out.println(stackByLinkedList.pop());
		System.out.println(stackByLinkedList.top());
		System.out.println(stackByLinkedList.pop());
	}
}
