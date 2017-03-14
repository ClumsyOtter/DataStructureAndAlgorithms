package com.tree.binarytree;


//二叉树基础功能
//节点的左子节点的值都小于这个节点的值，节点的右子节点的值都大于等于这个节点的值
//参考书籍<<数据库结构与算法分析>> mark allen weiss 
public abstract class BinaryTree<T extends Comparable<? super T>> {
	// 定义根节点
	private TreeNode<T> root;

	// 初始化二叉树
	public BinaryTree() {
		root = null;
	}

	// 删除二叉树
	public void delete() {
		root = null;
	}

	// 判断二叉树是否为空
	public boolean isEmpty() {
		return root == null;
	}

	// 判断节点是否在此树中
	public boolean contain(T x) {
		return contain(x, root);
	}

	private boolean contain(T x, TreeNode<T> root) {
		// 空树
		if (root == null)
			return false;
		// 节点的左子节点的值都小于这个节点的值，节点的右子节点的值都大于等于这个节点的值
		int compareResult = x.compareTo(root.getData());
		// 若被查找值比节点小 则访问左节点 反之访问右节点
		if (compareResult > 0) {
			contain(x, root.rightNode);
		} else if (compareResult < 0)
			contain(x, root.leftNode);
		return true;
	}

	// 查找树中最大的节点
	public TreeNode<T> findMax() throws Exception {
		if (isEmpty())
			throw new Exception("空树");
		return findMax(root);
	}

	private TreeNode<T> findMax(TreeNode<T> root) throws Exception {
		// 判断是否为空树
		if (root == null)
			return null;
		// 递归出口
		else if (null == root.rightNode)
			return root;
		// 节点的右子节点的值都大于等于这个节点的值
		return findMax(root.rightNode);

	}

	// 查找树中最小的数节点
	public TreeNode<T> findMin() throws Exception {
		if (isEmpty())
			throw new Exception("空树");
		return findMin(root);
	}

	private TreeNode<T> findMin(TreeNode<T> root2) {
		// 判断是否为空树
		if (root == null)
			return null;
		// 递归出口
		else if (null == root.leftNode)
			return root;
		// 节点的左子节点的值都小于这个节点的值
		return findMin(root.leftNode);
	}

	// 插入数据
	public void insert(T x) {
		root = insert(x, root);
	}

	private TreeNode<T> insert(T x, TreeNode<T> root) {
		// 递归出口
		if (null == root) {
			root = new TreeNode<>();
			root.setData(x);
			return root;
		}
		int compareResult = x.compareTo(root.getData());
		// 节点的左子节点的值都小于这个节点的值
		if (compareResult < 0) {
			return insert(x, root.leftNode);
		}
		// 节点的右子节点的值都大于等于这个节点的值
		return insert(x, root.rightNode);

	}

	// 删除数据
	public void remove(T x) {
		root = remove(x, root);
	}

	private TreeNode<T> remove(T x, TreeNode<T> root) {
		// 不存在x
		if (null == root)
			return root;
		int compareResult = x.compareTo(root.getData());
		if (compareResult < 0)
			remove(x, root.leftNode);
		else if (compareResult > 0)
			remove(x, root.rightNode);
		/*
		 * 找到了删除的节点但是他有两个子节点 一般的策略是将其右节点的最小值替换这个节点
		 */
		else if (root.leftNode != null && root.rightNode != null) {
			// 将右节点的最小值替换当前节点
			root.setData(findMin(root.rightNode).getData());
			// 删除右边最小值
			root.rightNode = remove(root.getData(), root.rightNode);
		} else
			// 只有一个子节点的节点
			root = (root.leftNode == null) ? root.rightNode : root.leftNode;
		return root;
	}

	// 遍历二叉树
	// 前序遍历
	public abstract void preOrder(TreeNode<T> root);

	// 中序遍历
	public abstract void inOrder(TreeNode<T> root);

	// 后序遍历
	public abstract void postOrder(TreeNode<T> root);
}
