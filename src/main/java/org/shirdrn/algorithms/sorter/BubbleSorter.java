package org.shirdrn.algorithms.sorter;

import org.shirdrn.algorithms.common.sorter.Sorter;
import org.shirdrn.algorithms.sorter.factory.SorterFactory;

/**
 * <B>冒泡排序算法类</B>
 * </br>
 * 基本思想：
 * </br>
 * 将被排序的记录数组R[0..n-1]垂直排列，每个记录R[i]看作是重量为R[i].key的气泡。根
 * 据轻气泡不能在重气泡之下的原则，从下往上扫描数组R：凡扫描到违反本原则的轻气泡，就使其
 * 向上"飘浮"。如此反复进行，直到最后任何两个气泡都是轻者在上，重者在下为止。
 * </br>
 * (1)初始: R[0..n-1]为无序区。
 * </br>
 * (2)第一趟扫描:从无序区底部向上依次比较相邻的两个气泡的重量，若发现轻者在下、重者
 * 在上，则交换二者的位置。即依次比较(R[n-1]，R[n-2])，(R[n-2]，R[n-3])，…，(R[1]，
 * R[0])；对于每对气泡(R[j+1]，R[j])，若R[j+1].key<R[j].key，则交换R[j+1]和R[j] 的内容。
 * </br>
 * 第一趟扫描完毕时，"最轻"的气泡就飘浮到该区间的顶部，即关键字最小的记录被放在最 高位置R[0]上。
 * </br>
 * (3)第二趟扫描: 扫描R[1..n-1]。扫描完毕时，"次轻"的气泡飘浮到R[1]的位置上……最后， 经过n-1 趟扫描可得到有序区R[0..n-1]。
 * </br>
 * <B>注意：</B>
 * </br>
 * 第i趟扫描时，R[0..i-1]和R[i..n-1]分别为当前的有序区和无序区。扫描仍是从无序区底
 * 部向上直至该区顶部。扫描完毕时，该区中最轻气泡飘浮到顶部位置R[i]上，结果是R[0..i]变 为新的有序区。
 * 
 * @author shirdrn
 * http://hi.baidu.com/shirdrn/item/16bc944ba06429e91e19bcd8
 */
public class BubbleSorter extends Sorter {

	@Override
	public void sort(int[] array) {
		Integer tmp; // 用于交换数据的暂存单元
		for (int i = array.length - 1; i >= 0; i--) { // 将数组最小索引一端视为“水面”
			// 将数组最小索引一端视为“水底”，“气泡”从“水底”向“水面”上浮
			// 因为i每增加1，就有一个上浮到最终排序位置，所以，只需要对1~i个元素进行交换排序
			for (int j = 1; j <= i; j++) {
				if (array[j - 1] < array[j]) { // 如果上浮过程中发现存在比当前元素小的，就交换，将小的交换到“水面”
					tmp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = tmp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] a = new int[] {94,12,34,76,26,9,0,37,55,76,37,5,68,83,90,37,12,65,76,49};
		SorterFactory.executeSorter(BubbleSorter.class, a);
		System.out.println(SorterFactory.print(a));
	}
}