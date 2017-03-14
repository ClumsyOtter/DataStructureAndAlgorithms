package doublylinkedlist;

public class Node<E> {
	// 储存的数据
	private E data;
	// 下一个节点
	private Node<E> next;
	// 上一个节点
	private Node<E> front;

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public Node<E> getFront() {
		return front;
	}

	public void setFront(Node<E> front) {
		this.front = front;
	}

}
