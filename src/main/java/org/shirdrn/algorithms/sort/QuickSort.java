package org.shirdrn.algorithms.sort;

/**
 * <B>快速排序算法类</B>
 * </br>
 * 基本思想：
 * </br>
 * 快速排序是C.R.A.Hoare于1962年提出的一种划分交换排序。它采用了一种分治的策略，
 * 通常称其为分治法(Divide-and-ConquerMethod)。
 * </br>
 * 分治法的基本思想是：将原问题分解为若干个规模更小但结构与原问题相似的子问题。递归 地解这些子问题，然后将这些子问题的解组合为原问题的解。
 * </br>
 * 快速排序的基本思想:
 * </br>
 * 设当前待排序的无序区为R[low..high]，利用分治法可将快速排序的基本思想描述为：
 * </br>
 * (1) 分解：
 * </br>
 * 在R[low..high]中任选一个记录作为基准(Pivot)，以此基准将当前无序区划分为左、
 * 右两个较小的子区间R[low..pivotpos-1)和R[pivotpos+1..high]，并使左边子区间中
 * 所有记录的关键字均小于等于基准记录(不妨记为pivot)的关键字pivot.key，右边的子区间
 * 中所有记录的关键字均大于等于pivot.key，而基准记录pivot则位于正确的位置(pivotpos) 上，它无须参加后续的排序。
 * </br>
 * 注意：
 * </br>
 * 划分的关键是要求出基准记录所在的位置pivotpos。划分的结果可以简单地表示为 (注意pivot=R[pivotpos])：
 * </br>
 * R[low..pivotpos-1].keys≤R[pivotpos].key≤R[pivotpos+1..high].keys
 * </br>
 * 其中low≤pivotpos≤high。
 * </br>
 * (2) 求解：
 * </br>
 * 通过递归调用快速排序对左、右子区间R[low..pivotpos-1]和R[pivotpos+1..high] 快速排序。
 * </br>
 * (3) 组合：
 * </br>
 * 因为当"求解"步骤中的两个递归调用结束时，其左、右两个子区间已有序。对快速排序而言， "组合"步骤无须做什么，可看作是空操作。
 * 
 * @author shirdrn
 * http://hi.baidu.com/shirdrn/item/e85a63ee6384aee0fa42bad8
 */
public class QuickSort {

	private Integer[] array;

	public QuickSort(Integer[] array) {
		this.array = array;
	}

	public void sort() {
		quickSort(0, array.length - 1);
	}

	/**
	 * 通过划分，基于分治思想，递归执行子任务排序最后合并
	 * @param low 数组首位置索引
	 * @param high 数组末位置索引
	 */
	private void quickSort(int low, int high) {
		int pivotPos; // 划分基准元素索引
		if (low < high) {
			pivotPos = partition(low, high);
			quickSort(low, pivotPos - 1); // 左划分递归快速排序
			quickSort(pivotPos + 1, high); // 右划分递归快速排序
		}
	}

	/**
	 * </br>
	 * 简单划分方法：
	 * </br>
	 * 第一步：
	 * </br>
	 * (初始化)设置两个指针i和j，它们的初值分别为区间的下界和上界，即i=low，i=high；
	 * 选取无序区的第一个记录R[i](即R[low])作为基准记录，并将它保存在变量pivot中；
	 * </br>
	 * 第二步：
	 * </br>
	 * 令j自high起向左扫描，直到找到第1个关键字小于pivot.key的记录R[j]，将R[j])移
	 * 至i所指的位置上，这相当于R[j]和基准R[i](即pivot)进行了交换，使关键字小于基准关键
	 * 字pivot.key的记录移到了基准的左边，交换后R[j]中相当于是pivot；然后，令i指针自i+1
	 * 位置开始向右扫描，直至找到第1个关键字大于pivot.key的记录R[i]，将R[i]移到i所指的
	 * 位置上，这相当于交换了R[i]和基准R[j]，使关键字大于基准关键字的记录移到了基准的右边，
	 * 交换后R[i]中又相当于存放了pivot；接着令指针j自位置j-1开始向左扫描，如此交替改变扫
	 * 描方向，从两端各自往中间靠拢，直至i=j时，i便是基准pivot最终的位置，将pivot放在 此位置上就完成了一次划分。
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int partition(int i, int j) {
		Integer pivot = array[i]; // 初始基准元素，如果quickSort方法第一次调用，pivot初始为数组第一个元素
		while (i < j) { // 两个指针从两边向中间靠拢，不能相交
			// 右侧指针向左移动
			while (j > i && array[j] >= pivot) {
				j--;
			}
			if (i < j) { // 如果在没有使指针i和j相交的情况下找到了array[j] >= 基准元素pivot
				array[i] = array[j]; // 基准元素放到了j指针处
				i++; // 左侧i指针需要向右移动一个位置
			}
			// 左侧指针向右移动
			while (i < j && array[i] <= pivot) {
				i++;
			}
			if (i < j) { // 如果在没有使指针i和j相交的情况下找到了array[i] <= 基准元素pivot
				array[j] = array[i]; // 基准元素放到了i指针处
				j--; // 右侧j指针需要向左移动一个位置
			}
		}
		array[i] = pivot; // 将基准元素放到正确的排序位置上
		return i;
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