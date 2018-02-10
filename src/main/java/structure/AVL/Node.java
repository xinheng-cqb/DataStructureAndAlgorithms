package structure.AVL;

/**
 * @author xinheng-cqb
 * @date 2018年2月9日
 * @introduce: 平衡二叉查找树节点
 */
public class Node<T extends Comparable<T>> {

	private T value; // 可比较的类,而且重写了equals和hashCode方法
	private int height; // 当前节点的高度
	private Node<T> leftNode;
	private Node<T> rightNode;

	public Node(T value) {
		this(null, null, value);
	}

	public Node(Node<T> leftNode, Node<T> rightNode, T value) {
		this.setLeftNode(leftNode);
		this.setRightNode(rightNode);
		this.setValue(value);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * @introduce: 更新当前节点树的高度值
	 * @return void
	 */
	public void updateHeight() {
		this.height = height(this);
	}

	/**
	 * @introduce: 递归计算某个节点的高度
	 * @param node
	 * @return int
	 */
	private int height(Node<T> node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = height(node.getLeftNode());
		int rightHeight = height(node.getRightNode());
		if (leftHeight > rightHeight) {
			return leftHeight + 1;
		} else {
			return rightHeight + 1;
		}
	}

	public Node<T> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node<T> leftNode) {
		this.leftNode = leftNode;
		updateHeight();
	}

	public Node<T> getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node<T> rightNode) {
		this.rightNode = rightNode;
		updateHeight();
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
}
