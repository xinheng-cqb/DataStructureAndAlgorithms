package algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xinheng-cqb
 * @date 2018年3月18日
 * @introduce:归并排序
 */
public class MergeSort implements BaseSort {

	public static void main(String[] args) {
		List<Integer> originList = Arrays.asList(new Integer[] { 9, 2, 6, 3, 8, 7, 4 });
		BaseSort sortAlgorithm = new MergeSort();
		sortAlgorithm.sort(originList);
		for (Integer num : originList) {
			System.out.print(num + " ");
		}
	}

	@Override
	public void sort(List<Integer> originList) {
		int step = 1;
		while (step < originList.size()) {
			mergeNearly(originList, step, originList.size());
			step = step * 2;
		}
	}

	private void mergeNearly(List<Integer> originList, int step, int length) {
		int i = 0;
		while (i + step * 2 - 1 < length) {
			merge(originList, i, i + step - 1, i + 2 * step - 1);
			i = i + step * 2;
		}
		if (i + step - 1 < length) {
			merge(originList, i, i + step - 1, length - 1);
		}
	}

	private void merge(List<Integer> originList, int low, int mid, int high) {
		int firstStartIndex = low;
		int secondStartIndex = mid + 1;
		List<Integer> orderlyList = new ArrayList<>();
		while (firstStartIndex <= mid && secondStartIndex <= high) {
			if (originList.get(firstStartIndex) > originList.get(secondStartIndex)) {
				orderlyList.add(originList.get(secondStartIndex));
				secondStartIndex++;
			} else {
				orderlyList.add(originList.get(firstStartIndex));
				firstStartIndex++;
			}
		}
		if (firstStartIndex > mid) {
			orderlyList.addAll(originList.subList(secondStartIndex, high + 1));
		} else {
			orderlyList.addAll(originList.subList(firstStartIndex, mid + 1));
		}
		for (int k = low, p = 0; k <= high; k++, p++) {
			originList.set(k, orderlyList.get(p));
		}
	}
}
