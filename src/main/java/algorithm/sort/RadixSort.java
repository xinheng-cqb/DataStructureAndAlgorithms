package algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @author xinheng-cqb
 * @date 2018年3月18日
 * @introduce:基数排序
 */
public class RadixSort implements BaseSort {

	public static void main(String[] args) {
		List<Integer> originList = Arrays.asList(new Integer[] { 9, 2, 6, 3, 8, 7, 4 });
		BaseSort sortAlgorithm = new RadixSort();
		sortAlgorithm.sort(originList);
		for (Integer num : originList) {
			System.out.print(num + " ");
		}
	}

	@Override
	public void sort(List<Integer> originList) {
		int digitLength = getMaxValueLength(originList);
		int radix = 10;
		Integer[] bucketArray = new Integer[originList.size()];
		int[] countArray = new int[radix];

		for (int i = 1; i <= digitLength; i++) {
			for (Integer num : originList) {
				int digit = getDigit(num, i);
				countArray[digit] = countArray[digit] + 1;
			}
			for (int j = 1; j < radix; j++) {
				countArray[j] = countArray[j] + countArray[j - 1];
			}
			for (int j = originList.size() - 1; j >= 0; j--) {
				int digit = getDigit(originList.get(j), i);
				bucketArray[countArray[digit] - 1] = originList.get(j);
				countArray[digit] = countArray[digit] - 1;
			}
			for (int a = 0; a < originList.size(); a++) {
				originList.set(a, bucketArray[a]);
			}
		}
	}

	private int getMaxValueLength(List<Integer> originList) {
		int max = originList.get(0);
		for (int i = 1; i < originList.size(); i++) {
			if (originList.get(i) > max) {
				max = originList.get(i);
			}
		}
		return (max + "").length();
	}

	private int getDigit(int num, int base) {
		int baseNum = (int) Math.pow(10, (base - 1));
		return num / baseNum % 10;
	}

}
