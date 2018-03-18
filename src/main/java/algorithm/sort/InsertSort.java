package algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @author xinheng-cqb
 * @date 2018年3月18日
 * @introduce:直接插入排序
 */
public class InsertSort implements BaseSort {

	public static void main(String[] args) {
		List<Integer> originList = Arrays.asList(new Integer[] { 9, 2, 6, 3, 8, 7, 4 });
		BaseSort sortAlgorithm = new InsertSort();
		sortAlgorithm.sort(originList);
		for (Integer num : originList) {
			System.out.print(num + " ");
		}
	}

	@Override
	public void sort(List<Integer> originList) {
		for (int i = 1; i < originList.size(); i++) {
			int temp = originList.get(i);
			int orderedIndex = i - 1;
			while (orderedIndex >= 0 && originList.get(orderedIndex) > temp) {
				originList.set(orderedIndex + 1, originList.get(orderedIndex));
				orderedIndex--;
			}
			originList.set(orderedIndex + 1, temp);
		}
	}

}
