package onewaylinkedlist;

//节点对象 对象应该包含储蓄的数据和指向下一个节点对象
//利用泛型 将储存的数据类型交给使用者来选择，大大提升灵活性
public class Node<E> {
	// 储存的数据
	private E data;
	// 下一个节点
	private Node<E> next;

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


}
