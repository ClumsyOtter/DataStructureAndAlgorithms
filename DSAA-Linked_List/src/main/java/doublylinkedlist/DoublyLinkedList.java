package doublylinkedlist;


public class DoublyLinkedList<E> {
	// 头结点
	private Node<E> headNode;
	// 尾节点
	private Node<E> endNode;

	// 在链表最后增加链表节点
	public void add(E obj) {
		Node<E> node = new Node<>();
		node.setData(obj);
		// 如果链表为空 则首尾节点都指向第一个节点
		if (headNode == null) {
			headNode = node;
			endNode = node;
		} else {
			// 不为空 将尾节点指向新增节点
			endNode.setNext(node);
			// 新节点指向尾节点
			node.setFront(endNode);
			// 尾节点后移
			endNode = node;
		}
	}

	// 在指定中间位置增加节点
	public void add(int index, E element) {
		// 创建新节点
		Node<E> node = new Node<>();
		node.setData(element);
		// 判断是否为空链表
		if (headNode == null) {
			throw new NullPointerException("空链表");
		}
		// 判断位置是否超出界限
		else if ((index < 0) || (index > this.size())) {
			throw new ArrayIndexOutOfBoundsException("超出范围");
		} else {
			// 首节点插入
			if (index == 0) {
				node.setNext(headNode);
				headNode.setFront(node);
				headNode = node;
			}
			// 尾节点插入(相当于在链表最后增加链表节点)
			else if (index == this.size()) {
				endNode.setNext(node);
				node.setFront(endNode);
				endNode = node;
			}
			// 中间插入
			else {
				// 保护
				Node<E> hnode = headNode;
				// 将首节点移动到插入节点的前一个节点
				for (int i = 1; i < index; i++) {
					hnode = hnode.getNext();
				}
				hnode.getNext().setFront(node);
				node.setNext(hnode.getNext());
				hnode.setNext(node);
				node.setFront(hnode);
			}
		}
	}

	// 移除此列表中指定位置上的元素
	public void remove(int index) {

		if (headNode == null) {
			throw new NullPointerException("空链表");
		} else if ((index < 0) || (index >= this.size())) {
			throw new ArrayIndexOutOfBoundsException("超出范围");
		} else {
			// 保护头结点
			Node<E> hnode = headNode;
			// 删除首节点
			if (index == 0) {
				headNode.getNext().setFront(null);
				headNode = headNode.getNext();
				// System.out.println(index + "号：" + hnode.getData().toString()
				// + "移除成功");
			}
			// 删除尾节点
			else if (index == (this.size() - 1)) {
				endNode.getFront().setNext(null);
				endNode = endNode.getFront();
			}
			// 删除中间节点
			else {
				// 移动到目标节点
				for (int i = 0; i < index; i++) {
					hnode = hnode.getNext();
				}
				hnode.getFront().setNext(hnode.getNext());
				hnode.getNext().setFront(hnode.getFront());
			}
		}
	}

	// 返回此列表中指定位置上的元素。
	public E get(int index) {
		if (headNode == null) {
			throw new NullPointerException("空链表");
		} else {
			// 遍历不到尾节点
			for (Node<E> node = headNode; node.getNext() != null; node = node.getNext()) {
				if (index == 0) {
					return node.getData();
				}
				index--;
			}
			// 尾节点单独检测 为什么是0因为index多运行了一次
			if (index == 0) {
				return endNode.getData();
			} else
				throw new ArrayIndexOutOfBoundsException("超出范围");
		}

	}

	// 返回此列表的元素数。
	public int size() {
		if (headNode == null) {
			return 0;
		} else {
			Node<E> node = headNode;
			int count = 0;
			while (node.getNext() != null) {
				node = node.getNext();
				count++;
			}
			return count + 1;
		}
	}

	// 用指定的元素替代此列表中指定位置上的元素。
	public E set(int index, E element) {
		// 删除指定位置的元素
		this.remove(index);
		// 插入指定位置元素
		this.add(index, element);
		return element;
	}


	public Node<E> getHeadNode() {
		return headNode;
	}

	public void setHeadNode(Node<E> headNode) {
		this.headNode = headNode;
	}

	public Node<E> getEndNode() {
		return endNode;
	}

	public void setEndNode(Node<E> endNode) {
		this.endNode = endNode;
	}

}
