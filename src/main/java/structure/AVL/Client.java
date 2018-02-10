package structure.AVL;

/**
 * @author xinheng-cqb
 * @date 2018年2月10日
 * @introduce:客户端测试类
 */
public class Client {
	public static void main(String[] args) {

		Tree<TestClass> tree = new Tree<TestClass>();
		tree.insert(new TestClass(24));
		tree.insert(new TestClass(32));
		tree.insert(new TestClass(10));
		tree.insert(new TestClass(6));
		tree.insert(new TestClass(28));
		tree.insert(new TestClass(36));
		tree.insert(new TestClass(26));
		tree.insert(new TestClass(37));

		// tree.insert(new TestClass(4));
		tree.remove(new TestClass(32));
		tree.preOrder(tree.getRoot());
		System.out.println("--------------");
		tree.inOrder(tree.getRoot());
	}
}
