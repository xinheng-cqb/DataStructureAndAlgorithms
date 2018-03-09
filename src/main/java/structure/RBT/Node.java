package structure.RBT;

/**
 * @author xinheng-cqb
 * @date 2018年2月9日
 * @introduce: 平衡二叉查找树节点
 */
public class Node<T extends Comparable<T>> {

	private T value; // 可比较的类,而且重写了equals和hashCode方法
	private boolean color;// 颜色 true为红色，false 为黑色
	private Node<T> leftNode;
	private Node<T> rightNode;
	private Node<T> parent;

	public Node(T value) {
		this(value, false, null, null, null);
	}

	public Node(T value, boolean color, Node<T> leftNode, Node<T> rightNode, Node<T> parent) {
		this.value = value;
		this.color = color;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
		this.parent = parent;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public boolean getColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public Node<T> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node<T> leftNode) {
		this.leftNode = leftNode;
	}

	public Node<T> getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node<T> rightNode) {
		this.rightNode = rightNode;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return this.value.toString() + (this.color ? "(R)" : "(B)");
	}

	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		Node<T> node = (Node<T>) obj;
		return node.getValue().equals(this.getValue());
	}

	@Override
	public int hashCode() {
		return this.value.hashCode();
	}
}
