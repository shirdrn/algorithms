package org.shirdrn.algorithms.sorter.factory;

import org.shirdrn.algorithms.common.sorter.Sorter;

public class SorterFactory {

	public static void executeSorter(Class<? extends Sorter> sorterClass, int[] array) {
		Sorter sorter = null;
		try {
			sorter = sorterClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Fail to create Sorter instance: ", e);
		}
		sorter.sort(array);
	}
	
	public static String print(int[] array) {
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
