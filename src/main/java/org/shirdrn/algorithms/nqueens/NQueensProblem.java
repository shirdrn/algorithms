package org.shirdrn.algorithms.nqueens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueensProblem {
	
	private int queenCount; // 皇后个数
	private int[] columns; // 用于回溯的数组，数组元素表示某个皇后所在列
	private final Set<List<Integer>> feasibleSolutionSpace = new HashSet<List<Integer>>(); // 可行解空间
	private int feasibleSolutionCount; // 可行解个数统计变量

	private final String CHESS = "●"; // 用实心圆圈代表棋子
	private final String BLANK = "○"; // 用空心圆圈代表棋盘空格子
	private String[][] chessBoard; // 棋盘二维数组，用于打印可行解对应的棋盘布局

	public NQueensProblem(int queenCount) {
		this.queenCount = queenCount;
		this.columns = new int[queenCount]; // 初始化
	}

	/**
	 * n-皇后问题回溯求解方法实现
	 */
	public void nQueens() {
		columns[0] = -1; // 初始化，将第一行的第1个皇后放到-1的位置(其实在棋盘外面，因为下面进入循环要自加后移1列)
		int row = 0; // 初始化为第1行，即对应第1个皇后
		while (row >= 0) {
			columns[row]++; // 在第row行方向上继续向后移动1列
			while (!this.place(row)) { // 寻找：当前第row行皇后与前row-1个放置好的皇后不冲突的位置
				columns[row]++;
			}
			// 第row个皇后一直在后移(沿着列方向)，可能有两种情况：
			// 第一种：第row个皇后在棋盘范围之内找到了与前row-1个皇后不冲突的位置
			// 第二种：第row个皇后在棋盘范围之外找到了与前row-1个皇后不冲突的位置
			if (columns[row] < queenCount) { // 如果是第一种情况
				if (row == queenCount - 1) { // 如果row==n-1表示第n-1(最后一行)个皇后找到的不冲突的位置
					this.addSolution(); // 求出一个可行解，加入解空间
					this.feasibleSolutionCount++; // 可行解统计计数加1
				} else { // 如果第row个皇后不是最后一行，需要继续找下一行皇后的合适位置
					row++; // row = row+1;
					columns[row] = -1; // 这里，先将第row+1个皇后放到第row+1行的-1的位置(其实在棋盘外面，因为进入循环要自加后移1列)
				}
			} else { // 如果是第二种情况
				// 回溯到上一行，重新寻找另一个合适的位置
				// 进入下一次循环，因为row=row-1，上一行的皇后所在的列为column[row]，需要继续沿着列方向后移寻找合适的位置
				row--;
			}
		}
	}

	/**
	 * 判断第row行的皇后是否满足问题要求
	 * <p>
	 * 此时，0~row-1行的皇后已经满足要求，只需要遍历0~row-1行，比较第k行皇后的位置是否合适，如果合适，
	 * 就返回true，继续执行n皇后求解的下一步
	 * 
	 * @param row
	 *            皇后放在第row行
	 * @return
	 */
	public Boolean place(Integer row) {
		int i = 0;
		// 如果第row行皇后分别与第0~row行皇后进行比较，判断是否互相攻击
		while (i < row) {
			// 如果第row行皇后与第0~row行皇后在同一列或者在同一对角线上
			if (columns[i] == columns[row] 
					|| Math.abs(i - row) == Math.abs(columns[i] - columns[row])) {
				return false;
			}
			i++;
		}
		return true;
	}

	/**
	 * 如果得到一个n-皇后问题的可行解，将该可行解加入到可行解空间中
	 */
	public void addSolution() {
		List<Integer> solution = new ArrayList<Integer>();
		for (Integer j : columns) {
			solution.add(j);
		}
		this.feasibleSolutionSpace.add(solution);
	}

	/**
	 * 初始化棋盘为空布局
	 */
	public void initChessBoard() {
		this.chessBoard = new String[queenCount][queenCount];
		for (int i = 0; i < queenCount; i++) {
			for (int j = 0; j < queenCount; j++) {
				this.chessBoard[i][j] = this.BLANK;
			}
		}
	}

	/**
	 * 根据n-皇后问题的可行解，打印棋盘布局
	 */
	public void displayChessBoardLayout() {
		for (List<Integer> solution : this.feasibleSolutionSpace) {
			this.initChessBoard(); // 初始化空棋盘
			// 根据一个可行解构造一种棋盘布局
			for (int i = 0; i < solution.size(); i++) {
				this.chessBoard[i][solution.get(i)] = this.CHESS;
			}
			// 打印出一种棋盘布局
			for (int i = 0; i < solution.size(); i++) {
				for (int j = 0; j < solution.size(); j++) {
					System.out.print(this.chessBoard[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	public Set<List<Integer>> getFeasibleSolutionSpace() {
		return feasibleSolutionSpace;
	}

	public int getFeasibleSolutionCount() {
		return feasibleSolutionCount;
	}
}