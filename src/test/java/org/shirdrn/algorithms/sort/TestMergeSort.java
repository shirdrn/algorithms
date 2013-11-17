package org.shirdrn.algorithms.sort;

import junit.framework.TestCase;

public class TestMergeSort extends TestCase {
	private MergeSort sort;
	private Integer[] array;

	@Override
	protected void setUp() throws Exception {
		array = new Integer[] { 94, 12, 34, 76, 26, 9, 0, 37, 55, 76, 37, 5, 68, 83, 90, 37, 12, 65, 76, 49 };
		sort = new MergeSort(array);
	}

	public void testSort() {
		// B(Before),A(After)
		System.out.println("(B)Sorting : " + this.sort.print());
		this.sort.sort();
		System.out.println("(A)Sorting : " + this.sort.print());
	}
}