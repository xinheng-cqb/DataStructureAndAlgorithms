package algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @author xinheng-cqb
 * @date 2018年3月18日
 * @introduce:快速排序
 */
public class QuickSort implements BaseSort {

	public static void main(String[] args) {
		List<Integer> originList = Arrays.asList(new Integer[] { 9, 2, 6, 3, 8, 7, 4 });
		BaseSort sortAlgorithm = new QuickSort();
		sortAlgorithm.sort(originList);
		for (Integer num : originList) {
			System.out.print(num + " ");
		}
	}

	@Override
	public void sort(List<Integer> originList) {
		quickInvoke(originList, 0, originList.size() - 1);
	}

	private void quickInvoke(List<Integer> originList, int left, int right) {
		if (left < right) {
			int mid = division(originList, left, right);
			quickInvoke(originList, left, mid);
			quickInvoke(originList, mid + 1, right);
		}
	}

	private int division(List<Integer> originList, int left, int right) {
		int baseNum = originList.get(left);
		while (left != right) {
			while (left < right && originList.get(right) > baseNum) {
				right--;
			}
			originList.set(left, originList.get(right));

			while (left < right && originList.get(left) < baseNum) {
				left++;
			}
			originList.set(right, originList.get(left));
		}
		originList.set(left, baseNum);
		return left;
	}

}
