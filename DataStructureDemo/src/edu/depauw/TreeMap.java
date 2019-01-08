package edu.depauw;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;

public class TreeMap<K, V> implements NavigableMap<K, V> {
	private class Node {
		K key;
		V value;
		Node left, right;

		public Node(Node left, K key, V value, Node right) {
			this.left = left;
			this.key = key;
			this.value = value;
			this.right = right;
		}
	}

	private Node root;
	private Comparator<? super K> comparator;
	private int size;

	// TODO constructors

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public boolean containsKey(Object key) {
		return findNode(key) != null;
	}

	@Override
	public boolean containsValue(Object value) {
		return containsValueAux(value, root);
	}

	private boolean containsValueAux(Object value, TreeMap<K, V>.Node node) {
		if (node == null) {
			return false;
		}
		return equal(node.value, value) || containsValueAux(value, node.left) || containsValueAux(value, node.right);
	}

	@Override
	public V get(Object key) {
		Node node = findNode(key);
		if (node != null) {
			return node.value;
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		Node node = findNode(key);
		if (node != null) {
			V old = node.value;
			node.value = value;
			return old;
		} else {
			root = putAux(root, key, value);
			return null;
		}
	}

	private Node putAux(Node node, K key, V value) {
		if (node == null) {
			Node result = new Node(null, key, value, null);
			size += 1;
			return result;
		}
		int cmp = compare(key, node.key);
		if (cmp < 0) {
			node.left = putAux(node.left, key, value);
		} else if (cmp > 0) {
			node.right = putAux(node.right, key, value);
		} else {
			// shouldn't happen
		}
		return node; // TODO balancing here?
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<? super K> comparator() {
		return comparator;
	}

	@Override
	public K firstKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K lastKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> lowerEntry(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K lowerKey(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> floorEntry(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K floorKey(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> ceilingEntry(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K ceilingKey(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> higherEntry(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K higherKey(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> firstEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> lastEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> pollFirstEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> pollLastEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<K, V> descendingMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<K> navigableKeySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<K> descendingKeySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<K, V> subMap(K fromKey, K toKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<K, V> headMap(K toKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<K, V> tailMap(K fromKey) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Check whether the element v matches the given object -- either they are equal
	 * or they are both null.
	 * 
	 * @param v
	 * @param o
	 * @return
	 */
	private boolean equal(V v, Object o) {
		return (v == null && o == null) || v.equals(o);
	}

	/**
	 * Compare the given values according to either the specified comparator or the
	 * "natural" ordering. In a more serious implementation, this would be inlined
	 * in various places so that the casts would only need to be done once per
	 * search.
	 * 
	 * @param key1
	 * @param key2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int compare(Object key1, K key2) {
		if (key1 == null && key2 == null) {
			return 0;
		}
		if (comparator != null) {
			K k = (K) key1;
			return comparator.compare(k, key2);
		} else {
			Comparable<? super K> k = (Comparable<? super K>) key1;
			return k.compareTo(key2);
		}
	}

	/**
	 * Return the node with the given key in this binary search tree, or null if not
	 * found.
	 * 
	 * @param key
	 * @return
	 */
	private Node findNode(Object key) {
		Node node = root;
		while (node != null) {
			int cmp = compare(key, node.key);
			if (cmp < 0) {
				node = node.left;
			} else if (cmp > 0) {
				node = node.right;
			} else {

			}
		}
		return node;
	}
}
