package com.tree.AVLtree;

public class AvlTree<T extends Comparable<? super T>> {

	/*
	 * 节点高度
	 */
	public int getNodeHight(AvlNode<T> node) {
		return node == null ? 0 : node.hight;
	}

	/*
	 * 将节点按照二叉搜索树插入 然后进行调整
	 */
	public AvlNode<T> insert(AvlNode<T> node, T x) {
		// 递归出口
		if (node == null) {
			return new AvlNode<T>(x);
		}
		int result = x.compareTo(node.date);
		// 二叉搜索树 左边节点小于根节点 右边节点大于根节点
		if (result < 0) {
			node.left = insert(node.left, x);
		} else if (result > 0)
			node.right = insert(node.right, x);
		else
			;
		return balance(node);
	}

	// 允许子树之间的高度差为1
	private static final int ALLOWED_IMBALANCE = 1;

	private AvlNode<T> balance(AvlNode<T> node) {
		if (node == null)
			return node;
		// 如果左子树比右子树高度超过一
		if (getNodeHight(node.left) - getNodeHight(node.right) > ALLOWED_IMBALANCE)
			// 如果左子树的左子树的高度大于等于右子树的高度则向左旋转
			if (getNodeHight(node.left.left) >= getNodeHight(node.left.right))
				node = rotateWithLeftChild(node);
			else
				// 如果左子树的右子树的高度大于左子树的高度则向双向右旋转
				node = doubleWithRightChild(node);
		// 如果右子树比左子树高度超过一
		else if (getNodeHight(node.right) - getNodeHight(node.left) > ALLOWED_IMBALANCE)
			// 如果右子树的右子树的高度大于等于左子树的高度则向右旋转
			if (getNodeHight(node.right.right) >= getNodeHight(node.right.left))
				node = rotateWithRightChild(node);
			else
				// 如果左子树的右子树的高度大于左子树的高度则向双向右旋转
				node = doubleWithLeftChild(node);
		// 平衡完成后将高度更新
		node.hight = Math.max(getNodeHight(node.left), getNodeHight(node.right) + 1);
		return node;
	}

	// 单向右旋转
	private AvlNode<T> rotateWithRightChild(AvlNode<T> node) {
		// 翻转节点为 node 和 node.left
		AvlNode<T> nodeR = node.right;
		// 向左翻转
		nodeR.left = node;
		node.right = nodeR.left;
		// 先更新node高度因为node的高度降低了
		node.hight = Math.max(getNodeHight(node.left), getNodeHight(node.right));
		// 更新nodeR nodeR高度上升了
		node.hight = Math.max(getNodeHight(nodeR.left), getNodeHight(nodeR.right));
		return nodeR;
	}

	// 双向右旋转
	private AvlNode<T> doubleWithRightChild(AvlNode<T> node) {
		// 所以先将节点的右树向左旋转使得其右树大于等于左树
		node.right = rotateWithLeftChild(node.right);
		// 然后便可以使用单向左旋转
		return rotateWithLeftChild(node);
	}

	// 单向左旋转
	private AvlNode<T> rotateWithLeftChild(AvlNode<T> node) {
		// 翻转节点为 node 和 node.left
		AvlNode<T> nodeL = node.left;
		// 向左翻转
		nodeL.right = node;
		node.left = nodeL.right;
		// 先更新node高度因为node的高度降低了
		node.hight = Math.max(getNodeHight(node.left), getNodeHight(node.right));
		// 更新nodeL nodeL高度上升了
		node.hight = Math.max(getNodeHight(nodeL.left), getNodeHight(nodeL.right));
		return nodeL;
	}

	// 双向左旋转
	private AvlNode<T> doubleWithLeftChild(AvlNode<T> node) {
		// 双向左旋转的条件是node的左子树的左子树小于右子树
		// 左旋转由于左旋转会将原node的左子树的右子树给旋转后的node的左子树
		// 由于原node的左子树的左子树小于右子树
		// node的高度=原node的左子树的右子树的高度+1大于旋转后的左子树
		// 所以先将节点的左树向右旋转使得其左树大于等于右树
		node.left = rotateWithRightChild(node.left);
		// 然后便可以使用单向左旋转
		return rotateWithLeftChild(node);
	}

	// 移除一个节点
	public AvlNode<T> remove(T x, AvlNode<T> node) {
		// 未找到 
		if (node == null) {
			return node;
		}
		int result = x.compareTo(node.date);
		if (result < 0)
			node.left = remove(x, node.left);
		else if (result > 0)
			node.right = remove(x, node.right);
		//两个子树
		else if (node.left != null && node.right != null) {
			node.date = findMin(node.right);
			node.right = remove(node.date, node.right);
		} else
			//一个子树
			node = node.left == null ? node.right : node.left;
		return balance(node);
	}

	private T findMin(AvlNode<T> node) {
		if (node == null) {
			return null;
		}
		// 递归出口
		else if (node.left == null)
			return node.date;
		return findMin(node.left);
	}
}
