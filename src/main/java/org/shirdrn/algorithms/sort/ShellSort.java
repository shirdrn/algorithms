package org.shirdrn.algorithms.sort;

/**
 * <B>希尔排序算法类</B>
 * </br>
 * 基本思想：
 * </br>
 * 先取一个小于n的整数d1作为第一个增量，把文件的全部记录分成d1个组。所有距离为dl的
 * 倍数的记录放在同一个组中。先在各组内进行直接插人排序；然后，取第二个增量d2<d1重复上 述的分组和排序，直至所取的增量dt=1(dt
 * <dt-l<…<d2<d1)，即所有记录放在同一组中进行 直接插入排序为止。
 * </br>
 * 该方法实质上是一种分组插入方法。
 * 
 * @author shirdrn
 * http://hi.baidu.com/shirdrn/item/e87fb994de42691c924f41d7
 */
public class ShellSort {
	private Integer[] array;

	public ShellSort(Integer[] array) {
		this.array = array;
	}

	public void sort() {
		int d = array.length;
		do {
			d /= 2;
			shellPass(d); // 根据逐渐减小的间隔增量，循环调用一趟排序
		} while (d > 1);
	}

	/**
	 * 希尔一趟排序
	 * 
	 * @param d
	 *            间隔增量
	 */
	private void shellPass(int d) {
		Integer tmp;
		for (int i = d; i < array.length; i++) { // 数组下标从0开始，初始i=d表示一趟排序中第二个元素
			tmp = array[i]; // array[i]的拷贝
			// 如果待处理的无序区第一个元素array[i] < 有序区最大的元素array[i-d]
			// 需要将有序区比array[i]大的元素向后移动
			if (array[i] < array[i - d]) {
				int j = i - d;
				while (j >= 0 && tmp < array[j]) {
					array[j + d] = array[j]; // 将左侧有序区中元素比array[i]大的array[j+d]后移
					j -= d;
				}
				// 如果array[i] >= 左侧有序区最大的array[i-d]，或者经过扫描移动后，找到一个比array[i]小的元素
				// 将右侧无序区第一个元素tmp = array[i]放到正确的位置上
				array[j + d] = tmp;
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