package com.ADT;

/*
 * 优先队列是至少允许下列两种操作的数据结构
 * insert(插入) deleteMin(删除最小) 
 * 优先队列的工作是 找出 返回 删除 优先队列中的最小或最大元素
 * 
 * 完全二叉堆实现优先队列
 * 利用完全二叉树的紧凑性将完全二叉树转化为数组形式
 */
public class BinaryHeap<T extends Comparable<? super T>> {
	// 堆
	private T[] heap;
	// 当前大小
	private int currentSize;
	// 默认大小
	private final static int DEFAULT_SIZE = 10;

	public BinaryHeap() {
		this(DEFAULT_SIZE);
	}

	// 打印二叉堆
	public void printHeap() {
		for (int i = 0; i < currentSize; i++)
			System.out.print(this.heap[i] + ",");
	}

	@SuppressWarnings("unchecked")
	public BinaryHeap(int size) {
		heap = (T[]) new Comparable[size];
	}

	// 将一个数组转化为二叉堆
	@SuppressWarnings("unchecked")
	public BinaryHeap(T[] items) {
		currentSize = items.length;
		heap = (T[]) new Comparable[(currentSize + 2) * 11 / 10];
		System.arraycopy(items, 0, heap, 0, items.length);
		bulidHeap();
	}

	/*
	 * 对外开放的方法
	 */

	// 插入堆
	public void insert(T t) {
		// 数据临界 扩一倍
		if (currentSize == heap.length)
			enlargeArray(heap.length * 2);
		// 当前数量加一
		currentSize++;
		// 插入节点的序号
		int i = currentSize - 1;
		while (i > 0 && t.compareTo(heap[(i - 1) / 2]) > 0) {
			// 插入数据大于其父节点的数据
			heap[i] = heap[(i - 1) / 2];
			// 下一个父节点
			i = (i - 1) / 2;
		}
		heap[i] = t;
	}

	// 找到最大值
	public T findMax() {
		if (currentSize > 0)
			return heap[0];
		return null;

	}

	// 删除最大值
	public T deleteMax() {
		T t = null;
		if (currentSize > 0) {
			t = heap[0];
			// 将最后一个元素替代第一个
			heap[0] = heap[currentSize - 1];
			currentSize--;
			percolateDown(0);
		}
		return t;
	}

	// 是否为空
	public boolean isEmpty() {
		return false;

	}

	// 将堆清空
	public void makeEmpty() {

	}

	/*
	 * 私人方法
	 */

	// 下滤 (先找到孩子的最大值在与小于孩子的父节点互换)
	private void percolateDown(int hole) {
		// 判断节点是否有孩子
		int left = hole * 2 + 1;
		T temp;
		// 如果左孩子存在
		if (left < currentSize) {
			// 如果右孩子存在
			if (left + 1 < currentSize) {
				// 若右孩子大于左孩子互换
				if (heap[left + 1].compareTo(heap[left]) > 0) {
					temp = heap[left + 1];
					heap[left + 1] = heap[left];
					heap[left] = temp;
				}
			}
			// 如果左节点大于父节点 互换
			if (heap[left].compareTo(heap[hole]) > 0) {
				temp = heap[hole];
				heap[hole] = heap[left];
				heap[left] = temp;
			}
		}
	}

	// 建立二叉堆
	public void bulidHeap() {
		// 调整所有节点 (currentSize表示当前堆中元素个数)
		for (int i = (currentSize - 2) / 2; i >= 0; i--) {
			percolateDown(i);
		}
	}

	// 动态扩充数组变为原来的两倍
	@SuppressWarnings("unchecked")
	private void enlargeArray(int newSize) {
		// 保存旧数据
		T[] temp = heap;
		// 扩一倍
		heap = (T[]) new Comparable[2 * heap.length];
		// 导入旧数据
		System.arraycopy(temp, 0, heap, 0, temp.length);
	}
}
