package edu.depauw;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.jupiter.api.Test;

class LinkedListTest {
	private String[] data = new String[] { "This", "is", "a", "test.", "This", "is", "only", "a", "test." };

	@Test
	void testSize() {
		List<Integer> list = new LinkedList<>();
		assertEquals(0, list.size());

		for (int i = 1; i <= 10; i++) {
			list.add(i);
			assertEquals(i, list.size());
		}

		for (int i = 1; i <= 10; i++) {
			list.remove(0);
			assertEquals(10 - i, list.size());
		}
	}

	@Test
	void testIsEmpty() {
		List<Integer> list = new LinkedList<>();
		assertTrue(list.isEmpty());

		for (int i = 1; i <= 10; i++) {
			list.add(i);
			assertFalse(list.isEmpty());
		}

		for (int i = 1; i <= 9; i++) {
			list.remove(0);
			assertFalse(list.isEmpty());
		}
		list.remove(0);
		assertTrue(list.isEmpty());
	}

	@Test
	void testClear() {
		List<Integer> list = new LinkedList<>();
		for (int i = 1; i <= 10; i++) {
			list.add(i);
		}
		list.clear();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}

	@Test
	void testAddE() {
		List<String> list = new LinkedList<>();
		for (int i = 0; i < data.length; i++) {
			assertTrue(list.add(data[i]));
			assertEquals(i + 1, list.size());
		}
	}

	@Test
	void testRemoveObject() {
		List<String> list = new LinkedList<>(Arrays.asList(data));

		// Note that data[0] == data[4], data[1] == data[5], data[2] == data[7], and
		// data[3] == data[8]
		assertTrue(list.remove(data[3]));
		assertTrue(list.remove(data[1]));
		assertTrue(list.remove(data[4]));
		assertTrue(list.remove(data[5]));
		assertFalse(list.remove(data[1]));
		assertFalse(list.remove("Hello"));
		assertTrue(list.remove(data[2]));
		assertTrue(list.remove(data[6]));
		assertFalse(list.remove(data[6]));
		assertTrue(list.remove(data[8]));
		assertFalse(list.remove(data[3]));
		assertTrue(list.remove(data[0]));
		assertFalse(list.remove(data[4]));
		assertTrue(list.remove(data[7]));
		assertTrue(list.isEmpty());
	}

	@Test
	void testContains() {
		List<String> list = new LinkedList<>(Arrays.asList(data));

		for (int i = 0; i < data.length; i++) {
			assertTrue(list.contains(data[i]));
		}
		assertFalse(list.contains("Hello"));
		assertFalse(list.contains("World"));
	}

	@Test
	void testContainsAll() {
		List<String> list = new LinkedList<>(Arrays.asList(data));

		List<String> test = new ArrayList<>(Arrays.asList(data));
		assertTrue(list.containsAll(test));
		test.remove(data[6]);
		assertTrue(list.containsAll(test));
		test.add("Hello");
		assertFalse(list.containsAll(test));
	}

	@Test
	void testAddAllCollection() {
		List<String> list = new LinkedList<>();
		list.addAll(Arrays.asList(data));
		assertEquals(data.length, list.size());
		int i = 0;
		for (String s : list) {
			assertEquals(data[i], s);
			i++;
		}
	}

	@Test
	void testAddAllIntCollection() {
		List<String> list = new LinkedList<>(Arrays.asList(data));
		list.addAll(1, Arrays.asList(data));
		assertEquals(2 * data.length, list.size());
		assertEquals(data[0], list.get(0));
		for (int i = 1; i <= data.length; i++) {
			assertEquals(data[i - 1], list.get(i));
		}
		for (int i = data.length + 1; i < 2 * data.length; i++) {
			assertEquals(data[i - data.length], list.get(i));
		}
		
		list.addAll(2 * data.length, Arrays.asList(data));
		assertEquals(3 * data.length, list.size());
		assertEquals(data[0], list.get(0));
		for (int i = 1; i <= data.length; i++) {
			assertEquals(data[i - 1], list.get(i));
		}
		for (int i = data.length + 1; i < 2 * data.length; i++) {
			assertEquals(data[i - data.length], list.get(i));
		}
		for (int i = 2 * data.length; i < 3 * data.length; i++) {
			assertEquals(data[i - 2 * data.length], list.get(i));
		}
	}

	@Test
	void testRemoveAll() {
		List<String> list = new LinkedList<>(Arrays.asList(data));
		list.removeAll(Arrays.asList("This", "is", "a", "test."));
		assertEquals(1, list.size());
		assertEquals("only", list.get(0));
	}

	@Test
	void testRetainAll() {
		List<String> list = new LinkedList<>(Arrays.asList(data));
		list.retainAll(Arrays.asList("This", "is", "a", "test."));
		assertEquals(8, list.size());
		for (int i = 0; i < 4; i++) {
			assertEquals(list.get(i), list.get(i + 4));
		}
	}

	@Test
	void testGet() {
		List<Integer> list = new LinkedList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5));
		assertEquals(3, list.get(0).intValue());
		assertEquals(1, list.get(1).intValue());
		assertEquals(4, list.get(2).intValue());
		assertEquals(1, list.get(3).intValue());
		assertEquals(5, list.get(4).intValue());
		assertEquals(9, list.get(5).intValue());
		assertEquals(2, list.get(6).intValue());
		assertEquals(6, list.get(7).intValue());
		assertEquals(5, list.get(8).intValue());
	}

	@Test
	void testSet() {
		List<Integer> list = new LinkedList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5));
		for (int i = 0; i < list.size(); i += 2) {
			list.set(i,  100 + i);
		}
		assertEquals(100, list.get(0).intValue());
		assertEquals(1, list.get(1).intValue());
		assertEquals(102, list.get(2).intValue());
		assertEquals(1, list.get(3).intValue());
		assertEquals(104, list.get(4).intValue());
		assertEquals(9, list.get(5).intValue());
		assertEquals(106, list.get(6).intValue());
		assertEquals(6, list.get(7).intValue());
		assertEquals(108, list.get(8).intValue());
	}

	@Test
	void testAddIntE() {
		List<String> list = new LinkedList<>(Arrays.asList(data));
		list.add(2, "only");
		assertEquals(10, list.size());
		for (int i = 0; i < 5; i++) {
			assertEquals(list.get(i), list.get(i + 5));
		}
		
		list.add(10, "Hello");
		assertEquals(11, list.size());
		for (int i = 0; i < 5; i++) {
			assertEquals(list.get(i), list.get(i + 5));
		}
		assertEquals("Hello", list.get(10));
	}

	@Test
	void testRemoveInt() {
		List<String> list = new LinkedList<>(Arrays.asList(data));
		list.remove(6);
		assertEquals(8, list.size());
		for (int i = 0; i < 4; i++) {
			assertEquals(list.get(i), list.get(i + 4));
		}
	}

	@Test
	void testIndexOf() {
		List<String> list = new LinkedList<>(Arrays.asList(data));
		assertEquals(0, list.indexOf("This"));
		assertEquals(1, list.indexOf("is"));
		assertEquals(2, list.indexOf("a"));
		assertEquals(3, list.indexOf("test."));
		assertEquals(6, list.indexOf("only"));
		assertEquals(-1, list.indexOf("Hello"));
	}

	@Test
	void testLastIndexOf() {
		List<String> list = new LinkedList<>(Arrays.asList(data));
		assertEquals(4, list.lastIndexOf("This"));
		assertEquals(5, list.lastIndexOf("is"));
		assertEquals(7, list.lastIndexOf("a"));
		assertEquals(8, list.lastIndexOf("test."));
		assertEquals(6, list.lastIndexOf("only"));
		assertEquals(-1, list.lastIndexOf("Hello"));
	}

	@Test
	void testIterator() {
		List<String> list = new LinkedList<>(Arrays.asList(data));
		Iterator<String> it = list.iterator();
		int i = 0;
		while (it.hasNext()) {
			assertEquals(data[i], it.next());
			i++;
		}
		
		it = list.iterator();
		while (it.hasNext()) {
			if (it.next().charAt(0) >= 'a') {
				it.remove();
			}
		}
		assertEquals(2, list.size());
		assertEquals("This", list.get(0));
		assertEquals("This", list.get(1));
	}

	@Test
	void testListIterator() {
		List<String> list = new LinkedList<>(Arrays.asList(data));
		ListIterator<String> it = list.listIterator();
		while (it.hasNext()) {
			assertEquals(data[it.nextIndex()], it.next());
		}
		while (it.hasPrevious()) {
			assertEquals(data[it.previousIndex()], it.previous());
		}
		
		it.add("Hello");
		assertEquals("Hello", list.get(0));
		assertEquals(data[0], it.next());
	}

	@Test
	void testListIteratorInt() {
		List<String> list = new LinkedList<>(Arrays.asList(data));
		ListIterator<String> it = list.listIterator(data.length);
		assertFalse(it.hasNext());
		while (it.hasPrevious()) {
			assertEquals(data[it.previousIndex()], it.previous());
		}
		while (it.hasNext()) {
			assertEquals(data[it.nextIndex()], it.next());
		}
		
		it.add("Hello");
		assertEquals("Hello", list.get(data.length));
		assertEquals("Hello", it.previous());
		it.remove();
		assertEquals(data.length, list.size());
		assertEquals(data[data.length - 1], it.previous());
	}

	@Test
	void testToArray() {
		List<String> list = new LinkedList<>(Arrays.asList(data));
		Object[] a = list.toArray();
		assertArrayEquals(data, a);
	}

	@Test
	void testToArrayTArray() {
		List<String> list = new LinkedList<>(Arrays.asList(data));
		String[] a = new String[100];
		String[] b = list.toArray(a);
		assertTrue(a == b);
		for (int i = 0; i < data.length; i++) {
			assertEquals(data[i], b[i]);
		}
		for (int i = data.length; i < 100; i++) {
			assertNull(b[i]);
		}
		
		String[] c = new String[0];
		String[] d = list.toArray(c);
		assertFalse(c == d);
		assertEquals(data.length, d.length);
		for (int i = 0; i < data.length; i++) {
			assertEquals(data[i], b[i]);
		}
	}

	@Test
	void testSubList() {
		List<Integer> list = new LinkedList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5));
		List<Integer> list2 = list.subList(2, 5);
		assertEquals(3, list2.size());
		assertEquals(4, list2.get(0).intValue());
		assertEquals(1, list2.get(1).intValue());
		assertEquals(5, list2.get(2).intValue());
		
		list2.set(1, 42);
		assertEquals(3, list.get(0).intValue());
		assertEquals(1, list.get(1).intValue());
		assertEquals(4, list.get(2).intValue());
		assertEquals(42, list.get(3).intValue());
		assertEquals(5, list.get(4).intValue());
		assertEquals(9, list.get(5).intValue());
		assertEquals(2, list.get(6).intValue());
		assertEquals(6, list.get(7).intValue());
		assertEquals(5, list.get(8).intValue());
		
		list2.add(37);
		assertEquals(4, list2.size());
		assertEquals(3, list.get(0).intValue());
		assertEquals(1, list.get(1).intValue());
		assertEquals(4, list.get(2).intValue());
		assertEquals(42, list.get(3).intValue());
		assertEquals(5, list.get(4).intValue());
		assertEquals(37, list.get(5).intValue());
		assertEquals(9, list.get(6).intValue());
		assertEquals(2, list.get(7).intValue());
		assertEquals(6, list.get(8).intValue());
		assertEquals(5, list.get(9).intValue());
		
		list2.remove(3);
		list2.remove(0);
		assertEquals(2, list2.size());
		assertEquals(3, list.get(0).intValue());
		assertEquals(1, list.get(1).intValue());
		assertEquals(42, list.get(2).intValue());
		assertEquals(5, list.get(3).intValue());
		assertEquals(9, list.get(4).intValue());
		assertEquals(2, list.get(5).intValue());
		assertEquals(6, list.get(6).intValue());
		assertEquals(5, list.get(7).intValue());
		
		list2.clear();
		assertEquals(0, list2.size());
		assertEquals(6, list.size());
		assertEquals(3, list.get(0).intValue());
		assertEquals(1, list.get(1).intValue());
		assertEquals(9, list.get(2).intValue());
		assertEquals(2, list.get(3).intValue());
		assertEquals(6, list.get(4).intValue());
		assertEquals(5, list.get(5).intValue());
	}
	
	@Test
	void testSubList2() {
		List<Integer> list = new LinkedList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5));
		List<Integer> list2 = list.subList(2, 9);
		assertEquals(7, list2.size());
		assertEquals(4, list2.get(0).intValue());
		assertEquals(1, list2.get(1).intValue());
		assertEquals(5, list2.get(2).intValue());
		assertEquals(9, list2.get(3).intValue());
		assertEquals(2, list2.get(4).intValue());
		assertEquals(6, list2.get(5).intValue());
		assertEquals(5, list2.get(6).intValue());
		
		list2.set(1, 42);
		assertEquals(3, list.get(0).intValue());
		assertEquals(1, list.get(1).intValue());
		assertEquals(4, list.get(2).intValue());
		assertEquals(42, list.get(3).intValue());
		assertEquals(5, list.get(4).intValue());
		assertEquals(9, list.get(5).intValue());
		assertEquals(2, list.get(6).intValue());
		assertEquals(6, list.get(7).intValue());
		assertEquals(5, list.get(8).intValue());
		
		list2.add(37);
		assertEquals(8, list2.size());
		assertEquals(3, list.get(0).intValue());
		assertEquals(1, list.get(1).intValue());
		assertEquals(4, list.get(2).intValue());
		assertEquals(42, list.get(3).intValue());
		assertEquals(5, list.get(4).intValue());
		assertEquals(9, list.get(5).intValue());
		assertEquals(2, list.get(6).intValue());
		assertEquals(6, list.get(7).intValue());
		assertEquals(5, list.get(8).intValue());
		assertEquals(37, list.get(9).intValue());
		
		list2.remove(7);
		list2.remove(0);
		assertEquals(6, list2.size());
		assertEquals(3, list.get(0).intValue());
		assertEquals(1, list.get(1).intValue());
		assertEquals(42, list.get(2).intValue());
		assertEquals(5, list.get(3).intValue());
		assertEquals(9, list.get(4).intValue());
		assertEquals(2, list.get(5).intValue());
		assertEquals(6, list.get(6).intValue());
		assertEquals(5, list.get(7).intValue());
		
		list2.clear();
		assertEquals(0, list2.size());
		assertEquals(2, list.size());
		assertEquals(3, list.get(0).intValue());
		assertEquals(1, list.get(1).intValue());
	}
	
	@Test
	void testReverse() {
		LinkedList<Integer> list = new LinkedList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5));
		list.reverse();
		assertEquals(3, list.get(8).intValue());
		assertEquals(1, list.get(7).intValue());
		assertEquals(4, list.get(6).intValue());
		assertEquals(1, list.get(5).intValue());
		assertEquals(5, list.get(4).intValue());
		assertEquals(9, list.get(3).intValue());
		assertEquals(2, list.get(2).intValue());
		assertEquals(6, list.get(1).intValue());
		assertEquals(5, list.get(0).intValue());
	}
}
