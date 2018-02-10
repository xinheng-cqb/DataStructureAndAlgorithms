package structure.AVL;

/**
 * @author xinheng-cqb
 * @date 2018年2月10日
 * @introduce: 测试类
 */
public class TestClass implements Comparable<TestClass> {

	private int value;

	public TestClass(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		TestClass tc = (TestClass) obj;
		if (tc.getValue() == this.value) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.value;
	}

	@Override
	public int compareTo(TestClass anotheClass) {
		if (this.value > anotheClass.getValue()) {
			return 1;
		} else if (this.value < anotheClass.getValue()) {
			return -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return this.value + "";
	}
}
