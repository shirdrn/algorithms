package org.shirdrn.algorithms.sorter;

import org.shirdrn.algorithms.common.sorter.Sorter;
import org.shirdrn.algorithms.sorter.factory.SorterFactory;

/**
 * <B>堆排序算法类</B>
 * </br>
 * 基本思想：
 * </br>
 * 堆的定义：
 * </br>
 * n个关键字序列Kl，K2，…，Kn称为堆，当且仅当该序列满足如下性质(简称堆性质)： (1) ki≤K2i且ki≤K2i+1
 * 或(2)Ki≥K2i且ki≥K2i+1(1≤i≤FLOOR(n/2))若将此序列
 * 所存储的向量R[1..n]看做是一棵完全二叉树的存储结构，则堆实质上是满足如下性质的完
 * 全二叉树：树中任一非叶结点的关键字均不大于(或不小于)其左右孩子(若存在)结点的关键 字。
 * </br>
 * 根结点(亦称为堆顶)的关键字是堆里所有结点关键字中最小者的堆称为小根堆。
 * </br>
 * 根结点(亦称为堆顶)的关键字是堆里所有结点关键字中最大者，称为大根堆。
 * </br>
 * 用大根堆排序的基本思想:
 * </br>
 * (1) 先将初始文件R[1..n]建成一个大根堆，此堆为初始的无序区;
 * </br>
 * (2) 再将关键字最大的记录R[1](即堆顶)和无序区的最后一个记录R[n]交换，由此得
 * 到新的无序区R[1..n-1]和有序区R[n]，且满足R[1..n-1].keys≤R[n].key;
 * </br>
 * (3) 由于交换后新的根R[1]可能违反堆性质，故应将当前无序区R[1..n-1]调整为堆。
 * 然后再次将R[1..n-1]中关键字最大的记录R[1]和该区间的最后一个记录R[n-1]交换，由
 * 此得到新的无序区R[1..n-2]和有序区R[n-1..n]，且仍满足关系
 * R[1..n-2].keys≤R[n-1..n].keys，同样要将R[1..n-2]调整为堆。
 * 
 * @author shirdrn
 * http://hi.baidu.com/shirdrn/item/25d1160dba8d9cdfdde5b0d8
 */
public class HeapSorter extends Sorter {

	public void sort(int[] array) {
		heapSort(array);
	}

	/**
	 * <p>
	 * 堆排序方法
	 * <p>
	 * 基于大根堆的堆排序方法
	 */
	private void heapSort(int[] array) {
		Integer tmp; // 用于交换的暂存单元
		buildHeap(array); // 执行初始建堆，并调整
		for (int i = 0; i < array.length; i++) {
			// 交换堆顶元素array[0]和堆中最后一个元素array[array.length-1-i]
			tmp = array[0];
			array[0] = array[array.length - 1 - i];
			array[array.length - 1 - i] = tmp;
			// 每次交换堆顶元素和堆中最后一个元素之后，都要对堆进行调整
			adjustHeap(array, 0, array.length - 1 - i);
		}
	}

	/**
	 * <p>
	 * 建堆方法
	 * <p>
	 * 调整堆中0~array.length/2个结点，保持堆的性质
	 * 
	 */
	private void buildHeap(int[] array) {
		// 求出当前堆中最后一个存在孩子结点的索引
		int pos = (array.length - 1) / 2;
		// 从该结点结点开始，执行建堆操作
		for (int i = pos; i >= 0; i--) {
			adjustHeap(array, i, array.length); // 在建堆过程中，及时调整堆中索引为i的结点
		}
	}

	/**
	 * <p>
	 * 调整堆的方法
	 * 
	 * @param s 待调整结点的索引
	 * @param m 待调整堆的结点的数量(亦即：排除叶子结点)
	 */
	private void adjustHeap(int[] array, int s, int m) {
		Integer tmp = array[s]; // 当前待调整的结点
		int i = 2 * s + 1; // 当前待调整结点的左孩子结点的索引(i+1为当前调整结点的右孩子结点的索引)
		while (i < m) {
			if (i + 1 < m && array[i] < array[i + 1]) { // 如果右孩子大于左孩子(找到比当前待调整结点大的孩子结点)
				i = i + 1;
			}
			if (array[s] < array[i]) {
				array[s] = array[i]; // 孩子结点大于当前待调整结点，将孩子结点放到当前待调整结点的位置上
				s = i; // 重新设置待调整的下一个结点的索引
				i = 2 * s + 1;
			} else { // 如果当前待调整结点大于它的左右孩子，则不需要调整，直接退出
				break;
			}
			array[s] = tmp; // 当前待调整的结点放到比其大的孩子结点位置上
		}
	}

	public static void main(String[] args) {
		int[] a = new int[] {43, 32, 1310, 9, 32, 10};
		SorterFactory.executeSorter(HeapSorter.class, a);
		System.out.println(SorterFactory.print(a));
	}
}
