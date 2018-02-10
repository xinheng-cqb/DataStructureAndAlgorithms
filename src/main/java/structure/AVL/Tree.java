package structure.AVL;

/**
 * @author xinheng-cqb
 * @date 2018年2月9日
 * @introduce: 平衡二叉查找树
 */
public class Tree<T extends Comparable<T>> {

	private Node<T> root;

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

	public void remove(T value) {
		this.root = remove(value, root);
	}

	private Node<T> remove(T value, Node<T> node) {
		if (node == null) {
			return null;
		}

		if (value.compareTo(node.getValue()) > 0) {
			// 从右子树中移除
			node.setRightNode(remove(value, node.getRightNode()));
			if (node.getLeftNode() != null) {
				// 判断左右的节点高度差是不是等于2，如果是就需要进行旋转
				if (node.getLeftNode().getHeight() - node.getRightNode().getHeight() == 2) {
					// 判断是要单旋还是双旋，判断的依据要插入的值和该节点右子节点比较，大就是单旋，小就是双旋
					if (node.getLeftNode().getRightNode().getHeight() > node.getLeftNode().getLeftNode().getHeight()) {
						node = doubleRotateWithLeft(node);
					} else {
						node = singleRotateRight(node);
					}
				}
			}
		} else if (value.compareTo(node.getValue()) < 0) {
			// 从左子树中移除
			node.setLeftNode(remove(value, node.getLeftNode()));
			if (node.getRightNode() != null) {
				if (node.getRightNode().getHeight() - node.getLeftNode().getHeight() == 2) {
					if (node.getRightNode().getRightNode().getHeight() < node.getRightNode().getLeftNode().getHeight()) {
						node = doubleRotateWithRight(node);
					} else {
						node = singleRotateLeft(node);
					}
				}
			}
		} else {
			// 已找到需要删除的元素,并且要删除的结点拥有两个子节点
			if (node.getLeftNode() != null && node.getRightNode() != null) {
				Node<T> directPostNode = getDirectPostNode(node);
				node.setValue(directPostNode.getValue());
				node.setRightNode(remove(node.getValue(), node.getRightNode()));
				if (node.getLeftNode().getHeight() == 2 && node.getRightNode() == null) {
					node = singleRotateRight(node);
				}
			} else {
				// 要删除的结点拥有一个子节点，或者没有
				node = node.getLeftNode() != null ? node.getLeftNode() : node.getRightNode();
			}
		}
		if (node != null) {
			node.updateHeight();
		}
		return node;
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

	public void insert(T value) {
		this.root = this.insert(value, root);
	}

	private Node<T> insert(T value, Node<T> node) {
		if (node == null) {
			node = new Node<>(value);
		} else if (value.compareTo(node.getValue()) > 0) {
			node.setRightNode(insert(value, node.getRightNode()));
			int leftHeight = 0;
			if (node.getLeftNode() != null) {
				leftHeight = node.getLeftNode().getHeight();
			}
			// 判断左右的节点高度差是不是等于2，如果是就需要进行旋转
			if (node.getRightNode().getHeight() - leftHeight == 2) {
				// 判断是要单旋还是双旋，判断的依据要插入的值和该节点右子节点比较，大就是单旋，小就是双旋
				if (value.compareTo(node.getRightNode().getValue()) < 0) {
					node = doubleRotateWithRight(node);
				} else {
					node = singleRotateLeft(node);
				}
			}

		} else if (value.compareTo(node.getValue()) < 0) {
			node.setLeftNode(insert(value, node.getLeftNode()));
			int rightHeight = 0;
			if (node.getRightNode() != null) {
				rightHeight = node.getRightNode().getHeight();
			}
			if (node.getLeftNode().getHeight() - rightHeight == 2) {
				if (value.compareTo(node.getLeftNode().getValue()) > 0) {
					node = doubleRotateWithLeft(node);
				} else {
					node = singleRotateRight(node);
				}
			}

		}
		// 重新计算各个结点的高度
		node.updateHeight();
		return node;
	}

	/**
	 * @introduce: 右旋 ： 左左 --> 向右单旋转
	 * @param node 失衡节点
	 * @return Node<T> 返回新的节点（代替失衡点）
	 */
	private Node<T> singleRotateRight(Node<T> node) {
		Node<T> rootNode = node.getLeftNode();
		node.setLeftNode(rootNode.getRightNode());
		rootNode.setRightNode(node);
		return rootNode;
	}

	/**
	 * @introduce: 左旋 ： 右右 --> 向左单旋转
	 * @param node 失衡节点
	 * @return Node<T> 返回新的节点（代替失衡点）
	 */
	private Node<T> singleRotateLeft(Node<T> node) {
		Node<T> rootNode = node.getRightNode();
		node.setRightNode(rootNode.getLeftNode());
		rootNode.setLeftNode(node);
		return rootNode;
	}

	/**
	 * @introduce: 双旋 ： 先右旋， 再左旋
	 * @param node 失衡节点
	 * @return Node<T> 返回新的节点（代替失衡点）
	 */
	private Node<T> doubleRotateWithRight(Node<T> node) {
		node.setRightNode(singleRotateRight(node.getRightNode()));
		return singleRotateLeft(node);
	}

	/**
	 * @introduce: 双旋 ： 先左旋， 再右旋
	 * @param node 失衡节点
	 * @return Node<T> 返回新的节点（代替失衡点）
	 */
	private Node<T> doubleRotateWithLeft(Node<T> node) {
		node.setLeftNode(singleRotateLeft(node.getLeftNode()));
		return singleRotateRight(node);
	}
}
