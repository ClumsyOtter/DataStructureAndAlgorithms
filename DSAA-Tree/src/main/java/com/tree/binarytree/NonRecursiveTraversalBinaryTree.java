package com.tree.binarytree;

//递归遍历二叉树
public class NonRecursiveTraversalBinaryTree<T extends Comparable<? super T>> extends BinaryTree<T> {

	/*
	 * 前序遍历
	 * 1.访问根节点
	 * 2.按照前序遍历的方式遍历左子树
	 * 3.按照前序遍历的方式遍历右字树
	 * @see com.tree.binarytree.BinaryTree#preOrder(com.tree.binarytree.TreeNode)
	 */
	@Override
	public void preOrder(TreeNode<T> root) {
		if (null != root) {
			//访问根节点
			System.out.println(root.getData());
			//访问左子树
			preOrder(root.leftNode);
			//访问右子树
			preOrder(root.rightNode);			
		}

	}
	/*
	 *中序遍历
	 *1.按照中序遍历的方式遍历左子树
	 *2.访问根节点
	 *3.按照中序遍历的方式遍历右子树
	 * @see com.tree.binarytree.BinaryTree#inOrder(com.tree.binarytree.TreeNode)
	 */
	@Override
	public void inOrder(TreeNode<T> root) {
		inOrder(root.leftNode);
		System.out.println(root.getData());
		inOrder(root.rightNode);

	}
	/*
	 * 后序遍历
	 * 1.按照后序遍历的方式遍历左子树
	 * 2.按照后序遍历的方式遍历右子树
	 * 3.访问根节点
	 * @see com.tree.binarytree.BinaryTree#postOrder(com.tree.binarytree.TreeNode)
	 */
	@Override
	public void postOrder(TreeNode<T> root) {
		postOrder(root.leftNode);
		postOrder(root.rightNode);
		System.out.println(root.getData());
	}

}
