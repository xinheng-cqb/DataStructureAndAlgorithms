package structure.BST;

/**
 * @author xinheng-cqb
 * @date 2018年2月8日
 * @introduce:二叉查找树
 */
public class Tree<T extends Comparable<T>> {
	private Node<T> root;

	public Node<T> find(T value) {
		Node<T> currentNode = root;
		while (currentNode != null && !currentNode.equals(value)) {
			if (currentNode.getValue().compareTo(value) > 0) {
				currentNode = currentNode.getLeftNode();
			} else {
				currentNode = currentNode.getRightNode();
			}
		}
		return currentNode;
	}

	public Node<T> getRoot() {
		return this.root;
	}

	public void insert(T value) {
		if (root == null) {
			root = new Node<T>(value);
			return;
		}
		Node<T> currentNode = root;
		Node<T> parentNode = null;
		boolean isLeftChild = true;

		// 查找第一个满足条件的空节点
		while (currentNode != null) {
			parentNode = currentNode;
			if (currentNode.getValue().compareTo(value) > 0) {
				currentNode = currentNode.getLeftNode();
				isLeftChild = true;
			} else {
				currentNode = currentNode.getRightNode();
				isLeftChild = false;
			}
		}

		if (isLeftChild) {
			parentNode.setLeftNode(new Node<T>(value));
		} else {
			parentNode.setRightNode(new Node<T>(value));
		}
	}

	/**
	 * @introduce: 先根遍历
	 * @return void
	 */
	public void preOrder(Node<T> rootNode) {
		if (rootNode != null) {
			System.out.println(rootNode);
			preOrder(rootNode.getLeftNode());
			preOrder(rootNode.getRightNode());
		}
	}

	/**
	 * @introduce: 中根遍历
	 * @return void
	 */
	public void inOrder(Node<T> rootNode) {
		if (rootNode != null) {
			inOrder(rootNode.getLeftNode());
			System.out.println(rootNode);
			inOrder(rootNode.getRightNode());
		}
	}

	/**
	 * @introduce: 后根遍历
	 * @return void
	 */
	public void postOrder(Node<T> rootNode) {
		if (rootNode != null) {
			postOrder(rootNode.getLeftNode());
			postOrder(rootNode.getRightNode());
			System.out.println(rootNode);
		}
	}

	public boolean delete(T value) {
		Node<T> currentNode = root;
		Node<T> parentNode = null;
		boolean isLeftChild = true;
		while (currentNode != null && !currentNode.getValue().equals(value)) {
			parentNode = currentNode;
			if (currentNode.getValue().compareTo(value) > 0) {
				currentNode = currentNode.getLeftNode();
				isLeftChild = true;
			} else {
				currentNode = currentNode.getRightNode();
				isLeftChild = false;
			}
		}
		if (currentNode == null) {
			System.out.println("--> 不存在该值对应的节点   <--");
			return false;
		}

		if (currentNode.getLeftNode() == null && currentNode.getRightNode() == null) {
			if (currentNode == root) {
				root = null;
			} else if (isLeftChild) {
				parentNode.setLeftNode(null);
			} else {
				parentNode.setRightNode(null);
			}
		} else if (currentNode.getLeftNode() != null && currentNode.getRightNode() == null) {
			if (currentNode == root) {
				root = currentNode.getLeftNode();
			} else if (isLeftChild) {
				parentNode.setLeftNode(currentNode.getLeftNode());
			} else {
				parentNode.setRightNode(currentNode.getLeftNode());
			}
		} else if (currentNode.getLeftNode() == null && currentNode.getRightNode() != null) {
			if (currentNode == root) {
				root = currentNode.getRightNode();
			} else if (isLeftChild) {
				parentNode.setLeftNode(currentNode.getRightNode());
			} else {
				parentNode.setRightNode(currentNode.getRightNode());
			}
		} else {
			Node<T> directPostNode = getDirectPostNode(currentNode);
			currentNode.setValue(directPostNode.getValue());
		}

		return true;
	}

	/**
	 * @introduce: 用于获取要替换待删除节点的新节点
	 * @param delNode
	 * @return Node
	 */
	private Node<T> getDirectPostNode(Node<T> delNode) {
		Node<T> directPostNode = delNode;
		Node<T> parentNode = null;
		Node<T> currentNode = delNode.getRightNode();
		while (currentNode != null) {
			parentNode = directPostNode;
			directPostNode = currentNode;
			currentNode = currentNode.getLeftNode();
		}
		if (directPostNode != delNode.getRightNode()) {
			parentNode.setLeftNode(directPostNode.getRightNode());
		}
		return directPostNode;
	}

}
