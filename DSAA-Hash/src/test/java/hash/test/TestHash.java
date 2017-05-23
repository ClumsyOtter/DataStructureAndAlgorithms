package hash.test;

import org.junit.Test;

import com.hash.DoubleQuadraticProbingHashTable;

public class TestHash {
	@Test
	public void test() {
		DoubleQuadraticProbingHashTable<Integer> dHashTable = new DoubleQuadraticProbingHashTable<>();
		for (int i = 0; i < 15; i++)
			dHashTable.insert(i);
		dHashTable.remove(5);
		for (int i = 0; i < 15; i++)
			System.out.println(i + " 是否存在  " + dHashTable.contain(i));
	}

}
