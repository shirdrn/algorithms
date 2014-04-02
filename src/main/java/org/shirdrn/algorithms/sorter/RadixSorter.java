package org.shirdrn.algorithms.sorter;

import org.shirdrn.algorithms.common.sorter.Sorter;

public class RadixSorter extends Sorter {
	
	private int radix;
	
	public RadixSorter() {
		radix = 10;
	}
	
	@Override
	public void sort(int[] array) {
		// 数组的第一维表示可能的余数0-radix，第二维表示array中的等于该余数的元素
		// 如：十进制123的个位为3，则bucket[3][] = {123}
		int[][] bucket = new int[radix][array.length]; 
		int distance = getDistance(array); // 表示最大的数有多少位
		int temp = 1;
		int round = 1;// 控制键值排序依据在哪一位
		while (round <= distance) {
			// 用来计数：数组counter[i]用来表示该位是i的数的个数
			int[] counter = new int[radix];
			// 将array中元素分布填充到bucket中，并进行计数
			for (int i = 0; i < array.length; i++) {
				int which = (array[i] / temp) % radix;
				bucket[which][counter[which]] = array[i];
				counter[which]++;
			}
			int index = 0;
			// 根据bucket中收集到的array中的元素，根据统计计数，在array中重新排列
			for (int i = 0; i < radix; i++) {
				if (counter[i] != 0)
					for (int j = 0; j < counter[i]; j++) {
						array[index] = bucket[i][j];
						index++;
					}
				counter[i] = 0;
			}
			temp *= radix;
			round++;
		}
	}
	
	private int getDistance(int[] array) {
		int max = computeMax(array);
		int digits = 0;
		int temp = max / radix;
		while(temp != 0) {
			digits++;
			temp = temp / radix;
		}
		return digits + 1;
	}
	
	private int computeMax(int[] array) {
		int max = array[0];
		for(int i=1; i<array.length; i++) {
			if(array[i]>max) {
				max = array[i];
			}
		}
		return max;
	}

	public static void main(String[] args) {
		RadixSorter sorter = new RadixSorter();
		int[] a = new int[] {94,12,34,76,26,9,0,37,55,76,37,5,68,83,90,37,12,65,76,49};
		sorter.sort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + ", ");
		}
	}
}