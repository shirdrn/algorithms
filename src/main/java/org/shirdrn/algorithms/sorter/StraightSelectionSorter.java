package org.shirdrn.algorithms.sorter;

import org.shirdrn.algorithms.common.sorter.Sorter;
import org.shirdrn.algorithms.sorter.factory.SorterFactory;

/**
 * <B>直接选择排序算法类</B>
 * </br>
 * 基本思想：
 * </br>
 * n个记录的文件的直接选择排序可经过n-1趟直接选择排序得到有序结果：
 * </br>
 * (1)初始状态：无序区为R[1..n]，有序区为空。
 * </br>
 * (2)第1趟排序: 在无序区R[1..n]中选出关键字最小的记录R[k]，将它与无序区的第1个记录R[1]
 * 交换，使R[1..1]和R[2..n]分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区。
 * </br>
 * ……
 * </br>
 * (3)第i趟排序: 第i趟排序开始时，当前有序区和无序区分别为R[1..i-1]和R[i..n](1≤i≤n-1)。
 * 该趟排序从当前无序区中选出关键字最小的记录R[k]，将它与无序区的第1个记录R[i]交换，使R[1..i]
 * 和R[i+1..n]分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区。
 * </br>
 * 这样，n个记录的文件的直接选择排序可经过n-1趟直接选择排序得到有序结果。　
 * 
 * @author shirdrn
 * http://hi.baidu.com/shirdrn/item/29bae4e787df25256dabb8d8
 */
public class StraightSelectionSorter extends Sorter {

	@Override
	public void sort(int[] array) {
		Integer tmp; // 用于交换数据的暂存单元
		for (int i = 0; i < array.length - 1; i++) { // 这里只要从0~array.length-2即可
			int k = i;
			for (int j = i + 1; j < array.length; j++) { // 该循环可以找到右侧无序区最小的元素array[k]
				if (array[k] > array[j]) {
					k = j;
				}
			}
			if (k != i) { // 如果array[i]不是无序区最小的，需要与无序区最小的进行交换
				tmp = array[i];
				array[i] = array[k];
				array[k] = tmp;
			}
			// 如果array[i]是无序区最小的元素，不需要执行交换
		}
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {43, 32, 1310, 9, 32, 10};
		SorterFactory.executeSorter(StraightSelectionSorter.class, a);
		System.out.println(SorterFactory.print(a));
	}

}
