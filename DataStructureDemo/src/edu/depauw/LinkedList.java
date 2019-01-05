package edu.depauw;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Simplified implementation of the standard library linked list collection. It
 * does not do fail-fast iterators.
 * 
 * @author bhoward
 *
 * @param <E>
 */
public class LinkedList<E> implements List<E> {
	/**
	 * Helper class to implement an iterator on this linked list.
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/**
		 * The Node to be used for a call to next().
		 */
		private Node current;

		/**
		 * The index of the current node.
		 */
		private int index;

		/**
		 * The Node from the last call to next() or previous(), if any, as long as there
		 * has been no intervening call to add() or remove().
		 */
		private Node last;

		/**
		 * Construct an iterator where the first call to next() will visit the given
		 * node, located at the given index.
		 * 
		 * @param node
		 * @param index
		 */
		public LinkedListIterator(Node node, int index) {
			this.current = node;
			this.index = index;
			this.last = null;
		}

		@Override
		public boolean hasNext() {
			return current != tail;
		}

		@Override
		public E next() {
			last = current;
			current = current.next;
			index++;
			return last.value;
		}

		@Override
		public boolean hasPrevious() {
			return current.prev != head;
		}

		@Override
		public E previous() {
			current = current.prev;
			last = current;
			index--;
			return last.value;
		}

		@Override
		public int nextIndex() {
			return index;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}

		@Override
		public void set(E e) {
			if (last == null) {
				throw new IllegalStateException();
			}
			last.value = e;
		}

		@Override
		public void add(E e) {
			insert(e, current);
			index++;
			last = null;
		}

		@Override
		public void remove() {
			if (last == null) {
				throw new IllegalStateException();
			}
			if (last == current) {
				current = last.next;
			} else {
				index--;
			}
			delete(last);
			last = null;
		}
	}

	/**
	 * A node in a doubly-linked list.
	 */
	private class Node {
		public E value;
		public Node prev, next;
	}

	/**
	 * A dummy head node; the first element of the list is stored in head.next,
	 * unless head.next is tail.
	 */
	private final Node head;

	/**
	 * A dummy tail node; the last element of the list is stored in tail.prev,
	 * unless tail.prev is head.
	 */
	private final Node tail;

	/**
	 * The number of elements in this list.
	 */
	private int size;

	/**
	 * Parent list of a sublist, or null for a non-sublist.
	 */
	private LinkedList<E> parent;

	/**
	 * Construct an empty linked list.
	 */
	public LinkedList() {
		this.parent = null;
		this.head = new Node();
		this.tail = new Node();
		clear();
	}

	/**
	 * Construct a linked list containing the elements from the given collection.
	 * 
	 * @param c
	 */
	public LinkedList(Collection<? extends E> c) {
		this();
		addAll(c);
	}

	/**
	 * Construct a sublist with the given head and tail nodes serving as sentinels.
	 * The given size should correspond to the actual number of nodes between head
	 * and tail (exclusive).
	 * 
	 * @param head
	 * @param tail
	 * @param size
	 */
	private LinkedList(LinkedList<E> parent, Node head, Node tail, int size) {
		this.parent = parent;
		this.head = head;
		this.tail = tail;
		this.size = size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		head.next = tail;
		tail.prev = head;
		addSize(-size);
	}

	@Override
	public boolean add(E e) {
		insert(e, tail);
		return true;
	}

	@Override
	public boolean remove(Object o) {
		for (Node node = head.next; node != tail; node = node.next) {
			if (equal(node.value, o)) {
				delete(node);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		for (Node node = head.next; node != tail; node = node.next) {
			if (equal(node.value, o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean modified = false;
		for (E e : c) {
			modified = add(e) || modified;
		}
		return modified;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean modified = false;
		Node node = locate(index);
		for (E e : c) {
			insert(e, node);
			modified = true;
		}
		return modified;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean modified = false;
		for (Node node = head.next; node != tail; node = node.next) {
			if (c.contains(node.value)) {
				delete(node);
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean modified = false;
		for (Node node = head.next; node != tail; node = node.next) {
			if (!c.contains(node.value)) {
				delete(node);
				modified = true;
			}
		}
		return modified;
	}

	@Override
	public E get(int index) {
		Node node = locateExisting(index);
		return node.value;
	}

	@Override
	public E set(int index, E element) {
		Node node = locateExisting(index);
		E old = node.value;
		node.value = element;
		return old;
	}

	@Override
	public void add(int index, E element) {
		Node node = locate(index);
		insert(element, node);
	}

	@Override
	public E remove(int index) {
		Node node = locateExisting(index);
		E old = node.value;
		delete(node);
		return old;
	}

	@Override
	public int indexOf(Object o) {
		int index = 0;
		for (Node node = head.next; node != tail; node = node.next, index++) {
			if (equal(node.value, o)) {
				return index;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		int index = size - 1;
		for (Node node = tail.prev; node != head; node = node.prev, index--) {
			if (equal(node.value, o)) {
				return index;
			}
		}
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		return listIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new LinkedListIterator(head.next, 0);
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		Node node = locate(index);
		return new LinkedListIterator(node, index);
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		int index = 0;
		for (Node node = head.next; node != tail; node = node.next, index++) {
			result[index] = node.value;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		T[] result = a;
		if (size > a.length) {
			// This incantation is from https://stackoverflow.com/questions/77387
			result = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		}
		int index = 0;
		for (Node node = head.next; node != tail; node = node.next, index++) {
			result[index] = (T) node.value;
		}
		while (index < size) {
			result[index] = null;
			index++;
		}
		return result;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		Node newHead = locateExisting(fromIndex).prev;
		Node newTail = locate(toIndex);
		int newSize = toIndex - fromIndex;
		return new LinkedList<>(this, newHead, newTail, newSize);
	}

	/**
	 * Reverse the nodes in this list. The current node steps forward through the
	 * original list (and temp is its successor), while last represents the most
	 * recent addition to the new ordering of the list, building back from the tail.
	 */
	public void reverse() {
		Node last = tail;
		Node current = head.next;
		while (current != tail) {
			Node temp = current.next;
			current.next = last;
			last.prev = current;
			last = current;
			current = temp;
		}
		last.prev = head;
		head.next = last;
	}

	/**
	 * Check whether the element e matches the given object -- either they are equal
	 * or they are both null.
	 * 
	 * @param e
	 * @param o
	 * @return
	 */
	private boolean equal(E e, Object o) {
		return (e == null && o == null) || e.equals(o);
	}

	/**
	 * Same as locate(index), but disallows index == size (so the node has to be an
	 * existing node in the list).
	 * 
	 * @param index
	 * @return
	 */
	private Node locateExisting(int index) {
		if (index == size) {
			throw new IndexOutOfBoundsException();
		}

		return locate(index);
	}

	/**
	 * Return the Node at the given index, or throw IndexOutOfBoundsException if
	 * index is invalid. If index == size, returns the (dummy) tail node.
	 * 
	 * @param index
	 * @return
	 */
	private Node locate(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		if (index < size / 2) {
			Node node = head.next;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		} else {
			Node node = tail;
			for (int i = size; i > index; i--) {
				node = node.prev;
			}
			return node;
		}
	}

	/**
	 * Insert the given value in a new node in front of the given node.
	 * 
	 * @param e
	 * @param node
	 */
	private void insert(E e, Node node) {
		Node temp = new Node();
		temp.value = e;
		temp.next = node;
		temp.prev = node.prev;
		temp.next.prev = temp;
		temp.prev.next = temp;
		addSize(1);
	}

	/**
	 * Delete the given node from the list.
	 * 
	 * @param node
	 */
	private void delete(Node node) {
		node.next.prev = node.prev;
		node.prev.next = node.next;
		addSize(-1);
	}

	private void addSize(int n) {
		size += n;
		if (parent != null) {
			parent.addSize(n);
		}
	}
}
