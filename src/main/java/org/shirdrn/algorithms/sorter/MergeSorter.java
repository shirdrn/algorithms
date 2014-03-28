package org.shirdrn.algorithms.sorter;

import org.shirdrn.algorithms.common.sorter.Sorter;
import org.shirdrn.algorithms.sorter.factory.SorterFactory;

/**
 * <B>归并排序算法类</B></br>
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
 * http://hi.baidu.com/shirdrn/item/dd74c7a197277fd85bf191db
 */
public class MergeSorter extends Sorter {

	@Override
	public void sort(int[] array) {
		int[] auxArray = new int[array.length];
		mergeSort(array, auxArray, 0, array.length - 1);
	}

	/**
	 * 基于分治思想，执行归并排序
	 * 
	 * @param low 待排序数组下标下界
	 * @param high 待排序数组下标上界
	 */
	private void mergeSort(int[] array, int[] auxArray, int low, int high) {
		int dividedIndex = 0; // 分治位置索引变量
		if (low < high) {
			dividedIndex = (low + high) / 2; // 计算分治位置(采用简单的二分思想)
			mergeSort(array, auxArray, low, dividedIndex); // 左侧递归归并排序
			mergeSort(array, auxArray, dividedIndex + 1, high); // 右侧递归归并排序
			merge(array, auxArray, low, dividedIndex, high); // 合并分治结果
		}
	}

	private void merge(int[] array, int[] auxArray, int low, int dividedIndex, int high) {
		int i = low; // 指向左半分区数组的指针
		int j = dividedIndex + 1; // 指向右半分区数组的指针
		int auxPtr = 0; // 指向辅助区数组的指针
		// 合并两个有序数组：array[low..dividedIndex]与array[dividedIndex+1..high]。
		while (i <= dividedIndex && j <= high) { // 将两个有序的数组合并，排序到辅助数组auxArray中
			if (array[i] > array[j]) { // 左侧数组array[low..dividedIndex]中的array[i]大于右侧数组array[dividedIndex+1..high]中的array[j]
				auxArray[auxPtr++] = array[j++];
			} else {
				auxArray[auxPtr++] = array[i++];
			}
		}
		// 如果array[low..dividedIndex].lenght>array[dividedIndex+1..high].length，经过上面合并
		// array[low..dividedIndex]没有合并完，则直接将array[low..dividedIndex]中没有合并的元素复制到辅助数组auxArray中去
		while (i <= dividedIndex) {
			auxArray[auxPtr++] = array[i++];
		}
		// 如果array[low..dividedIndex].lenght<array[dividedIndex+1..high].length，经过上面合并
		// array[dividedIndex+1..high]没有合并完，则直接将array[dividedIndex+1..high]中没有合并的元素复制到辅助数组auxArray中去
		while (j <= high) {
			auxArray[auxPtr++] = array[j++];
		}
		// 最后把辅助数组auxArray的元素复制到原来的数组中去，归并排序结束
		for (auxPtr = 0, i = low; i <= high; i++, auxPtr++) {
			array[i] = auxArray[auxPtr];
		}
	}

	public static void main(String[] args) {
		int[] a = new int[] {43, 32, 1310, 9, 32, 10};
		SorterFactory.executeSorter(MergeSorter.class, a);
		System.out.println(SorterFactory.print(a));
	}
}
