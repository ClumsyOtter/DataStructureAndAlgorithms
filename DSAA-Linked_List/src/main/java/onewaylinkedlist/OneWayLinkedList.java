package onewaylinkedlist;

public class OneWayLinkedList<E> {

	private Node<E> headNode;
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
			// 尾节点后移
			endNode = node;
		}
	}

	// 在指定中间位置增加节点
	public void add(int index, E element) {
		Node<E> node = new Node<>();
		node.setData(element);

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
				headNode = node;
			}
			// 尾节点插入
			else if (index == this.size()) {
				endNode.setNext(node);
				endNode = node;
			}
			// 中间插入
			else {
				Node<E> hnode = headNode;
				// 将首节点移动到插入节点的前一个节点
				for (int i = 1; i <= index - 1; i++) {
					hnode = hnode.getNext();
				}
				node.setNext(hnode.getNext());
				hnode.setNext(node);
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
				headNode = headNode.getNext();
				//System.out.println(index + "号：" + hnode.getData().toString() + "移除成功");
			}
			// 删除尾节点
			else if (index == (this.size() - 1)) {
				while (hnode.getNext() != null) {
					hnode=hnode.getNext();
				}
				hnode.setNext(null);	
				//System.out.println(index + "号：" + endNode.getData() + " 移除成功");
				endNode = hnode;
			}
			// 删除中间节点
			else {
				for (int i = 1; i < index; i++) {
					hnode = hnode.getNext();
				}
				//E data = hnode.getNext().getData();
				hnode.setNext(hnode.getNext().getNext());
				//System.out.println(index + "号：" + data.toString() + " 移除成功");
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
		//删除指定位置的元素
		this.remove(index);
		//插入指定位置元素
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
