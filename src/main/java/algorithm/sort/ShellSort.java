package algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @author xinheng-cqb
 * @date 2018年3月18日
 * @introduce:希尔排序
 */
public class ShellSort implements BaseSort {

	public static void main(String[] args) {
		List<Integer> originList = Arrays.asList(new Integer[] { 9, 2, 6, 3, 8, 7, 4 });
		BaseSort sortAlgorithm = new ShellSort();
		sortAlgorithm.sort(originList);
		for (Integer num : originList) {
			System.out.print(num + " ");
		}
	}

	@Override
	public void sort(List<Integer> originList) {
		int step = originList.size() / 2;
		while (step > 0) {
			for (int i = step; i < originList.size(); i++) {
				int temp = originList.get(i);
				int originIndex = i - step;
				while (originIndex >= 0 && originList.get(originIndex) > temp) {
					originList.set(originIndex + step, originList.get(originIndex));
					originIndex -= step;
				}
				originList.set(originIndex + step, temp);
			}
			step = step / 2;
		}
	}

}
