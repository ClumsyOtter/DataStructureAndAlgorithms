package com.test;

import org.junit.Test;

import com.ADT.BinaryHeap;

public class TestADT {
	@Test
	public void test() {
		Integer[] ss = { 155, 50, 3, 5, 89, 7, 1, 2, 45, 68, 4, 5, 3 };
		BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(ss);
		for (Integer integer : ss) {
			System.out.print(binaryHeap.deleteMax() + ",");
			binaryHeap.bulidHeap();
		}
	}
}
