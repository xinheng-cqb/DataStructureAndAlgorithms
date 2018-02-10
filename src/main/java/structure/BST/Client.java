package structure.BST;

/**
 * @author xinheng-cqb
 * @date 2018年2月8日
 * @introduce:客户端测试类
 */
public class Client {
	public static void main(String[] args) {
		Tree<TestClass> tree = new Tree<TestClass>();
		tree.insert(new TestClass(3));
		tree.insert(new TestClass(6));
		tree.insert(new TestClass(4));
		tree.insert(new TestClass(8));
		tree.insert(new TestClass(2));
		tree.insert(new TestClass(1));
		tree.insert(new TestClass(5));
		tree.insert(new TestClass(9));
		tree.insert(new TestClass(0));
		// tree.insert(new TestClass(7));
		tree.delete(new TestClass(6));
		tree.preOrder(tree.getRoot());
		System.out.println("--------------");
		tree.inOrder(tree.getRoot());
	}
}
