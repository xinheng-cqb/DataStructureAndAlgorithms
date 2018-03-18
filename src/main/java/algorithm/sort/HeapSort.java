package algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @author xinheng-cqb
 * @date 2018年3月18日
 * @introduce:堆排序
 */
public class HeapSort implements BaseSort {

	public static void main(String[] args) {
		List<Integer> originList = Arrays.asList(new Integer[] { 9, 2, 6, 3, 8, 7, 4 });
		BaseSort sortAlgorithm = new HeapSort();
		sortAlgorithm.sort(originList);
		for (Integer num : originList) {
			System.out.print(num + " ");
		}
	}

	@Override
	public void sort(List<Integer> originList) {
		for (int i = originList.size() / 2 + 1; i > 0; i--) {
			initHeap(originList, i, originList.size());
		}
		for (int i = originList.size() - 1; i > 0; i--) {
			int temp = originList.get(i);
			originList.set(i, originList.get(0));
			originList.set(0, temp);
			initHeap(originList, 0, i);
		}
	}

	private void initHeap(List<Integer> originList, int parent, int length) {
		int seed = originList.get(parent);
		int child = 2 * parent + 1;
		while (child < length) {
			if (child + 1 < length && originList.get(child + 1) > originList.get(child)) {
				child++;
			}
			if (originList.get(child) < seed) {
				break;
			}
			originList.set(parent, originList.get(child));
			parent = child;
			child = 2 * parent + 1;
		}
		originList.set(parent, seed);
	}

}
