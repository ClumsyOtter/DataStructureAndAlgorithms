package com.hash;

/*
 * 解决哈希冲突
 * 平方探测法
 */
public class QuadraticProbingHashTable<T> {
	private static final int DEFAULT_SIZE = 11;

	public QuadraticProbingHashTable() {
		this(DEFAULT_SIZE);
	}

	QuadraticProbingHashTable(int DEFAULT_SIZE) {
		allocateArray(DEFAULT_SIZE);
	}

	@SuppressWarnings("unchecked")
	private void allocateArray(int arraySize) {
		// 根据arraySize的数值获取下一个素数
		array = new HashEntry[nextPrime(arraySize)];
	}

	public void remove(T element) {
		// findPos这个位置对象可能为null也可能与element相同
		int findPos = findPos(element);
		// 找到了与element相同的对象
		if (isActive(findPos)) {
			// 惰性删除
			array[findPos].isActive = false;
			// 当前插入数据大小减1
			currentSize--;
		}
	}

	// 哈希对象
	private static class HashEntry<T> {
		// 保存数据
		public T element;
		// 是否被删除 （惰性删除）
		public boolean isActive;

		public HashEntry(T element) {
			this(element, true);
		}

		public HashEntry(T element, boolean isActive) {
			this.element = element;
			this.isActive = isActive;
		}
	}

	// 哈希数组
	private HashEntry<T>[] array;
	// 数组当前大小
	private int currentSize;

	// 插入数据
	public void insert(T element) {
		int findPos = findPos(element);
		// 若element已经存在于该hash中返回空
		if (isActive(findPos)) {
			return;
		}
		// 若不存在则插入
		array[findPos] = new HashEntry<T>(element);
		// 插入成功后已插入数据大小加1
		currentSize++;
		// 如果当前的已插入的数据大小比数组的1/2还要大则要更新数组
		// 因为平方探测法只能探索数组大小的1/2
		if (currentSize > array.length / 2) {
			rehash();
		}
	}

	// 将数组扩大至原来的两倍
	private void rehash() {
		// 保存旧数据
		HashEntry<T>[] oldArray = array;
		// 将数组扩大两倍
		allocateArray(oldArray.length * 2);
		// 将旧数据导入新数据
		for (HashEntry<T> hashEntry : oldArray) {
			// 由于旧数据中有一半未插入数据所以这里要筛选一下
			if (hashEntry != null && hashEntry.isActive)
				insert(hashEntry.element);
		}
	}

	// element是否存在
	public boolean contain(T element) {
		// findPos这个位置对象可能为null也可能与element相同
		int findPos = findPos(element);
		// 判断findPos这个位置的对象是否与element相同
		return isActive(findPos);
	}

	// 判断该位置对象是否活跃
	private boolean isActive(int currentPos) {
		// 若该位置对象不为null且是活跃的 则为活跃
		return array[currentPos] != null && array[currentPos].isActive;
	}

	/*
	 * 利用平方探测法查找冲突后下一个存储点
	 */
	private int findPos(T element) {
		// 偏移量
		int offset = 1;
		// 利用哈希找到此对象的哈希位置
		int currentPos = myhash(element);
		// 循环查找直到找到一个为null或者返回与element对象相同的位置
		while (array[currentPos] != null && !array[currentPos].element.equals(element)) {
			// 移动到下一个探测点
			currentPos += offset;
			// 偏移量更新
			offset += 2;
			// 超出数组范围
			if (currentPos >= array.length) {
				currentPos -= array.length;
			}
		}
		return currentPos;
	}

	private int myhash(T t) {
		// 获取哈希值
		int hashval = t.hashCode();
		// 限定范围
		hashval %= array.length;
		if (hashval < 0)
			hashval += array.length;
		return hashval;
	}

	private static int nextPrime(int n) {
		// 如果是偶数则加1变奇数（所有的偶数除2以外都不是素数）
		if (n % 2 == 0)
			n++;
		// 如果不是素数则加2
		for (; !isPrime(n); n += 2)
			;
		return n;
	}

	public static boolean isPrime(int n) {
		// 2,3是素数
		if (n == 2 || n == 3)
			return true;
		// 1是特例不是素数
		// 若被偶数整除不是素数
		if (n == 1 || n % 2 == 0)
			return false;
		// 若被奇数整除则不是素数 (3,5,7,9,11,13,15,17.......)
		// 若i*i>=n 此时i已经是遍历n的所有可能公因数
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;
		// 若通过上述的判断则为素数。
		return true;
	}
}
