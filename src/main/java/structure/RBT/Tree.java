package structure.RBT;

/**
 * @author xinheng-cqb
 * @date 2018年3月2日
 * @introduce: 红黑树
 */
public class Tree<T extends Comparable<T>> {

	private Node<T> root;

	public void preOrder() {
		preOrder(root);
	}

	/**
	 * @introduce: 先根遍历
	 * @return void
	 */
	private void preOrder(Node<T> rootNode) {
		if (rootNode != null) {
			System.out.println(rootNode);
			preOrder(rootNode.getLeftNode());
			preOrder(rootNode.getRightNode());
		}
	}

	public void inOrder() {
		inOrder(root);
	}

	/**
	 * @introduce: 中根遍历++
	 * @return void
	 */
	private void inOrder(Node<T> rootNode) {
		if (rootNode != null) {
			inOrder(rootNode.getLeftNode());
			System.out.println(rootNode);
			inOrder(rootNode.getRightNode());
		}
	}

	public void postOrder() {
		postOrder(root);
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

	public void insert(T value) {
		insert(new Node<T>(value));
	}

	/**
	 * @introduce: 先将节点先当作普通节点插入
	 * @param node
	 * @return void
	 */
	private void insert(Node<T> node) {
		Node<T> parent = null;
		Node<T> currentNode = root;
		boolean left = false;
		while (currentNode != null) {
			parent = currentNode;
			if (node.getValue().compareTo(currentNode.getValue()) > 0) {
				currentNode = currentNode.getRightNode();
				left = false;
			} else {
				currentNode = currentNode.getLeftNode();
				left = true;
			}
		}
		if (parent != null) {
			if (left) {
				parent.setLeftNode(node);
			} else {
				parent.setRightNode(node);
			}
			node.setParent(parent);
		} else {
			this.root = node;
		}
		node.setColor(true);

		insertFixUp(node);
	}

	/**
	 * @introduce: 插入的节点后进行相应的调整，使得整颗树还能符合红黑树的条件
	 * @param node
	 * @return void
	 */
	private void insertFixUp(Node<T> node) {
		Node<T> parent = null;
		Node<T> grandparent = null;

		// 父节点存在，并且颜色为红色
		while ((parent = node.getParent()) != null && parent.getColor()) {
			grandparent = parent.getParent();

			// 父节点是祖父节点的左孩子
			if (parent == grandparent.getLeftNode()) {
				// 叔叔节点是红色
				Node<T> uncle = grandparent.getRightNode();
				if (uncle != null && uncle.getColor()) {
					uncle.setColor(false);
					parent.setColor(false);
					grandparent.setColor(true);
					node = grandparent;
					continue;
				}

				// 叔叔节点是黑色，且当前是右孩子
				if (node == parent.getRightNode()) {
					leftRotate(parent);
					Node<T> temp = parent;
					parent = node;
					node = temp;
				}

				// 其他情况：叔叔是黑色，且当前是左孩子
				parent.setColor(false);
				grandparent.setColor(true);
				rightRotate(grandparent);
			} else {
				// 父节点是祖父节点的右孩子，只是旋转的方法不一致其他类似
				Node<T> uncle = grandparent.getRightNode();
				if (uncle != null && uncle.getColor()) {
					uncle.setColor(false);
					parent.setColor(false);
					grandparent.setColor(true);
					node = grandparent;
					continue;
				}

				if (parent.getLeftNode() == node) {
					rightRotate(parent);
					Node<T> temp = parent;
					parent = node;
					node = temp;
				}

				parent.setColor(false);
				grandparent.setColor(true);
				leftRotate(grandparent);
			}
		}
		// 将根节点设为黑色
		this.root.setColor(false);
	}

	public void remove(T value) {
		Node<T> node = search(this.root, value);
		if (node != null) {
			remove(node);
		}
	}

	private void remove(Node<T> node) {
		Node<T> parent = node.getParent();
		// 被删除节点存在左右孩子节点
		if (node.getLeftNode() != null && node.getRightNode() != null) {
			Node<T> replace = node.getRightNode();
			while (replace.getLeftNode() != null) {
				replace = replace.getLeftNode();
			}
			if (parent != null) {
				if (parent.getLeftNode() == node) {
					parent.setLeftNode(replace);
				} else {
					parent.setRightNode(replace);
				}
			} else {
				this.root = replace;
			}
			Node<T> replaceChild = replace.getRightNode();
			Node<T> replaceParent = replace.getParent();
			boolean color = replace.getColor();

			// 将原先的node节点的右边孩子节点和replace节点进行关系梳理
			if (replaceParent == node) {
				replaceParent = replace;
			} else {
				if (replaceChild != null) {
					replaceChild.setParent(replaceParent);
				}
				replaceParent.setLeftNode(replaceChild);
				replace.setRightNode(node.getRightNode());
				node.getRightNode().setParent(replace);
			}

			replace.setParent(node.getParent());
			replace.setColor(node.getColor());
			replace.setLeftNode(node.getLeftNode());
			node.getLeftNode().setParent(replace);

			if (!color) {
				removeFixUp(replaceChild, replaceParent);
			}
			node = null;
			return;
		}

		Node<T> child = null;
		if (node.getLeftNode() != null) {
			child = node.getLeftNode();
		} else {
			child = node.getRightNode();
		}
		boolean color = node.getColor();
		if (child != null) {
			child.setParent(parent);
		}
		if (parent != null) {
			if (parent.getLeftNode() == node) {
				parent.setLeftNode(child);
			} else {
				parent.setRightNode(child);
			}
		} else {
			this.root = child;
		}

		if (!color) {
			removeFixUp(child, parent);
		}
		node = null;
	}

	/**
	 * @introduce: 修复因为删除导致的不平衡
	 * @param node 要修正的节点
	 * @param parent
	 * @return void
	 */
	private void removeFixUp(Node<T> node, Node<T> parent) {
		Node<T> other = null;

		while (node == null || !node.getColor() && node != this.root) {
			if (parent.getLeftNode() == node) {
				other = node.getRightNode();
				// 情形1：node的兄弟节点是红色的
				if (other.getColor()) {
					other.setColor(false);
					parent.setColor(true);
					leftRotate(parent);
					other = parent.getRightNode();
				}
				// 情形2：node的兄弟节点是黑色的，且旗下的两个子节点也都是黑色的
				if ((other.getLeftNode() == null || !other.getLeftNode().getColor())
						&& (other.getRightNode() == null || !other.getRightNode().getColor())) {
					other.setColor(true);
					node = parent;
					parent = node.getParent();
				} else {
					// 情形3：node的兄弟节点是黑色的，且旗下的左子节点是红色，右子节点是黑色
					if (other.getRightNode() == null || !other.getRightNode().getColor()) {
						other.getLeftNode().setColor(false);
						other.setColor(true);
						rightRotate(other);
						other = parent.getRightNode();
					}
					// 情形3：node的兄弟节点是黑色的，且旗下的右子节点是红色，左子节点任意颜色
					other.setColor(parent.getColor());
					parent.setColor(false);
					other.getRightNode().setColor(false);
					leftRotate(parent);
					node = this.root;
					break;
				}
			} else { // 与上面的对称
				other = parent.getLeftNode();
				if (other.getColor()) {
					other.setColor(false);
					parent.setColor(true);
					rightRotate(parent);
					other = parent.getLeftNode();
				}

				if ((other.getLeftNode() == null || !other.getLeftNode().getColor())
						&& (other.getRightNode() == null || !other.getRightNode().getColor())) {
					other.setColor(true);
					node = parent;
					parent = node.getParent();
				} else {
					if (other.getLeftNode() == null || !other.getLeftNode().getColor()) {
						other.getRightNode().setColor(false);
						other.setColor(true);
						leftRotate(other);
						other = parent.getLeftNode();
					}

					other.setColor(parent.getColor());
					parent.setColor(false);
					other.getLeftNode().setColor(false);
					rightRotate(parent);
					node = this.root;
					break;
				}
			}
		}
		if (node != null) {
			node.setColor(false);
		}
	}

	/**
	 * @introduce: 定位value所在的节点，如果不存在返回null
	 * @param rootNode
	 * @param value
	 * @return Node<T>
	 */
	private Node<T> search(Node<T> rootNode, T value) {
		if (rootNode == null) {
			System.out.println("要删除的节点不存在");
			return null;
		}
		if (value.compareTo(rootNode.getValue()) == 0) {
			return rootNode;
		} else if (value.compareTo(rootNode.getValue()) > 0) {
			return search(rootNode.getRightNode(), value);
		} else {
			return search(rootNode.getLeftNode(), value);
		}
	}

	/**
	 * @introduce: 左旋 --》口诀 ： 右右左(适用于平衡二叉树)
	 * @param node
	 * @return void
	 */
	private Node<T> leftRotate(Node<T> node) {
		Node<T> root = node.getRightNode();
		node.setRightNode(root.getLeftNode());
		if (root.getLeftNode() != null) {
			root.getLeftNode().setParent(node);
		}
		root.setParent(node.getParent());
		if (node.getParent() == null) {
			this.root = root;
		} else {
			if (node == node.getParent().getLeftNode()) {
				node.getParent().setLeftNode(root);
			} else {
				node.getParent().setRightNode(root);
			}
		}
		root.setLeftNode(node);
		return root;
	}

	/**
	 * @introduce: 右旋 --》口诀 ： 左左右(适用于平衡二叉树)
	 * @param node
	 * @return void
	 */
	private Node<T> rightRotate(Node<T> node) {
		Node<T> root = node.getLeftNode();
		node.setLeftNode(root.getRightNode());
		if (root.getRightNode() != null) {
			root.getRightNode().setParent(node);
		}
		root.setParent(node.getParent());
		if (node.getParent() == null) {
			this.root = root;
		} else {
			if (node == node.getParent().getRightNode()) {
				node.getParent().setRightNode(root);
			} else {
				node.getParent().setLeftNode(root);
			}
		}
		root.setRightNode(node);
		return root;
	}
}
