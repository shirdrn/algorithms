package org.shirdrn.algorithms.sort;

/**
 * <B>直接插入排序算法类</B>
 * </br>
 * 基本思想：
 * </br>
 * 假设待排序的记录存放在数组R[0..n-1]中。初始时，R[0]自成1个有序区，无序区为 R[1..n-1]。
 * 从i=1起直至i=n-1为止，依次将R[i]插入当前的有序区R[0..i-1]中， 生成含n个记录的有序区。
 * 
 * @author shirdrn
 * http://hi.baidu.com/shirdrn/item/907adbe122ce9a394ddcafdb
 */
public class StraightInsertionSort {

	/**
	 * 待排序数组
	 */
	private Integer[] array;

	public StraightInsertionSort(Integer[] array) {
		this.array = array;
	}

	/**
	 * 实现排序方法
	 */
	public void sort() {
		Integer tmp;
		for (int i = 1; i < array.length; i++) {
			tmp = array[i]; // array[i]的拷贝
			// 如果右侧无序区第一个元素array[i] < 左侧有序区最大的array[i-1]，
			// 需要将有序区比array[i]大的元素向后移动。
			if (array[i] < array[i - 1]) {
				int j = i - 1;
				while (j >= 0 && tmp < array[j]) { // 从右到左扫描有序区
					array[j + 1] = array[j]; // 将左侧有序区中元素比array[i]大的array[j+1]后移
					j--;
				}
				// 如果array[i]>=左侧有序区最大的array[i-1]，或者经过扫描移动后，找到一个比array[i]小的元素
				// 将右侧无序区第一个元素tmp = array[i]放到正确的位置上
				array[j + 1] = tmp;
			}
		}
	}

	/**
	 * 输出数组元素
	 */
	public String print() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i != array.length - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
}
