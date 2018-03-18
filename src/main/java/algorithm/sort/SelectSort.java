package algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @author xinheng-cqb
 * @date 2018年3月18日
 * @introduce:直接选择排序
 */
public class SelectSort implements BaseSort {

	public static void main(String[] args) {
		List<Integer> originList = Arrays.asList(new Integer[] { 9, 2, 6, 3, 8, 7, 4 });
		BaseSort sortAlgorithm = new SelectSort();
		sortAlgorithm.sort(originList);
		for (Integer num : originList) {
			System.out.print(num + " ");
		}
	}

	@Override
	public void sort(List<Integer> originList) {
		for (int i = 0; i < originList.size() - 1; i++) {
			int smallestIndex = i;
			for (int j = i + 1; j < originList.size(); j++) {
				if (originList.get(j) < originList.get(smallestIndex)) {
					smallestIndex = j;
				}
			}
			if (smallestIndex != i) {
				int temp = originList.get(i);
				originList.set(i, originList.get(smallestIndex));
				originList.set(smallestIndex, temp);
			}
		}
	}

}
