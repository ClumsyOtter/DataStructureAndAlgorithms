package com.tree.binarytree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

//非递归遍历二叉树
public class RecursiveTraversalBinaryTree<T extends Comparable<? super T>> extends BinaryTree<T> {
	/*
	 * 非递归的前序遍历 在递归方法中需要采用一个栈来记录当前的节点以便完成了左子树后可以返回到右子树
	 * 
	 * @see
	 * com.tree.binarytree.BinaryTree#preOrder(com.tree.binarytree.TreeNode)
	 */
	@Override
	public void preOrder(TreeNode<T> root) {
		if (root != null) {
			// 创建栈
			Stack<TreeNode<T>> stack = new Stack<>();
			// 利用无限循环来模拟递归
			while (true) {
				while (root != null) {
					// 访问根节点
					System.out.println(root.getData());
					stack.push(root);
					// 访问左节点
					root = root.leftNode;
				}
				// 如果栈为空退出
				if (stack.isEmpty())
					break;
				// 弹栈来依次访问右节点
				root = stack.pop();
				root = root.rightNode;
			}

		}

	}

	@Override
	public void inOrder(TreeNode<T> root) {
		if (root != null) {
			// 创建栈
			Stack<TreeNode<T>> stack = new Stack<>();
			// 利用无限循环来模拟递归
			while (true) {
				while (root != null) {
					stack.push(root);
					// 访问左节点
					root = root.leftNode;
				}
				// 如果栈为空退出
				if (stack.isEmpty())
					break;
				// 弹栈来依次访问右节点
				root = stack.pop();
				// 访问根节点
				System.out.println(root.getData());

				root = root.rightNode;
			}
		}

	}

	/*
	 * 前序和后序的遍历 当元素出栈后就不需要再次访问，但是在后序遍历中每个节点要访问两次 这就意味着 在遍历完左子树后需要访问当前节点
	 * 之后遍历完右子树后还得访问该当前节点 解决方法 当从栈中出栈一个元素时 检查这个元素与栈顶元素的右子节点是否相同 若相同 则完成了左右树的遍历
	 * 此时只需将栈顶元素出栈一次并输出该节点数据即可
	 * 
	 * @see
	 * com.tree.binarytree.BinaryTree#postOrder(com.tree.binarytree.TreeNode)
	 */
	@Override
	public void postOrder(TreeNode<T> root) {
		if (root != null) {
			// 创建栈
			Stack<TreeNode<T>> stack = new Stack<>();
			// 利用无限循环来模拟递归
			while (true) {
				if (root != null) {
					stack.push(root);
					// 访问左节点
					root = root.leftNode;
				} else {
					// 如果栈为空退出
					if (stack.isEmpty())
						return;
					// 如果该节点的右节点为null 则已到底 否则还有右节点没有访问
					if (stack.peek().rightNode == null) {
						// 则弹栈
						root = stack.pop();
						// 访问节点
						System.out.println(root.getData());
						// 如果该节点是父节点的右节点则证明其父节点的左右子树都访问完成
						if (root == stack.peek().rightNode) {
							root = stack.pop();
							System.out.println(root.getData());
						}
					}
					// 栈不为空则访问其右节点
					if (!stack.isEmpty())
						root = stack.peek().rightNode;
				}
			}
		}
	}

	/*
	 * 层次遍历 层次遍历定义 1.访问根节点 2.在访问 i层时将i+1层的结点按照顺序保存在队列中 3.进入下一层并访问所有节点
	 * 4.重复上述操作直至所有层访问完
	 */
	public void LevelOrder(TreeNode<T> root) {
		// 创建队列
		Queue<TreeNode<T>> queue = new ArrayDeque<>();
		if (root == null)
			return;
		// 利用队列先进先出的原则来实现层次遍历
		queue.add(root);
		while (!queue.isEmpty()) {
			//弹出队列首节点
			TreeNode<T> node = queue.remove();
			System.out.println(node.getData());
			//将节点的左右子节点加入队列
			if(node.leftNode != null)
				queue.add(node.leftNode);
			if(node.rightNode != null)
				queue.add(node.rightNode);
		}
	}
}
