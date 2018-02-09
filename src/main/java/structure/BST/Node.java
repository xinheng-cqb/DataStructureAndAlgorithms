package structure.BST;

/**
 * @author xinheng-cqb
 * @date 2018年2月8日
 * @introduce: 二叉查找树节点
 */
public class Node<T extends Comparable<T>> {

	private T value; // 可比较的类,而且重写了equals和hashCode方法
	private Node<T> leftNode;
	private Node<T> rightNode;

	public Node(T value) {
		this.value = value;
	}

	public void displayNode() {
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
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

	@Override
	public String toString() {
		return this.value.toString();
	}
}
