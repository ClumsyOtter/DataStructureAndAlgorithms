package com.hash.separatechaining;

import java.util.LinkedList;
import java.util.List;

//分离链接法
/*
 * 为执行一次查找，我们使用散列函数来确定究竟遍历哪个链表
 * 然后我们在被确定的链表中执行一次查找。为执行insert 我们检查相应的链表看看该元素是否已经处在合适位置。
 * 
 */
public class SeparateChainingHashTable<T> {
	// 构造函数
	public SeparateChainingHashTable() {
		// 默认大小
		this(DEFAULT_TABLE_SIZE);
	}

	@SuppressWarnings("unchecked")
	public SeparateChainingHashTable(int size) {
		// 初始化链表数组
		linkList = new LinkedList[nextPrime(size)];
		for (int i = 0; i < linkList.length; i++) {
			linkList[i] = new LinkedList<>();
		}
	}

	// 清空哈希表
	public void makeEmpty() {
		for (int i = 0; i < linkList.length; i++) {
			linkList[i].clear();
		}
		currentSize = 0;
	}

	// 判断哈希表中是否包含t
	public boolean contain(T t) {
		// 利用哈希计算公式计算出其哈希值，在根据哈希值找到对应的链表
		List<T> list = linkList[myhash(t)];
		return list.contains(t);
	}

	// 在哈希表中插入数据
	public void insert(T t) {
		List<T> list = linkList[myhash(t)];
		if (!list.contains(t)) {
			list.add(t);
			if (++currentSize > linkList.length) {
				// 超出范围
				rehash();
			}
		}
	}

	// 在哈希表中移除数据
	public void remove(T t) {
		List<T> list = linkList[myhash(t)];
		if (list.contains(t)) {
			list.remove(t);
			currentSize--;
		}
	}

	private static final int DEFAULT_TABLE_SIZE = 11;

	private List<T>[] linkList; // 数据数组
	private int currentSize; // 数据占据数组的大小

	private void rehash() {

	}

	private int myhash(T t) {
		int hashval = t.hashCode();
		hashval %= linkList.length;
		if (hashval < 0)
			hashval += linkList.length;
		return hashval;
	}

	//下一个素数
	private static int nextPrime(int n) {
		//如果是偶数则加1变奇数（所有的偶数除2以外都不是素数）
		if (n % 2 == 0)
			n++;
		//如果不是素数则加2
		for (; !isPrime(n); n += 2)
			;

		return n;
	}

	/**
	  *是否为素数
	  *素数，有无限个。素数定义为在大于1的自然数中，
	  *除了1和它本身以外不再有其他因数的数称为素数。
	 */
	private static boolean isPrime(int n) {
		//2,3是素数
		if (n == 2 || n == 3)
			return true;
		//1是特例不是素数 
		//若被偶数整除不是素数
		if (n == 1 || n % 2 == 0)
			return false;
		//若被奇数整除则不是素数 (3,5,7,9,11,13,15,17.......)
		//若i*i>=n 此时i已经是遍历n的所有可能公因数
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;
		//若通过上述的判断则为素数。
		return true;
	}
}
