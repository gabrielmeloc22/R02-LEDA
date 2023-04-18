package sorting.divideAndConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if ((leftIndex < rightIndex) && (leftIndex >= 0) && (rightIndex <= array.length - 1) && (array.length != 0)) {
			if (leftIndex != rightIndex) {
				int middle = (leftIndex + rightIndex) / 2;
				sort(array, leftIndex, middle);
				sort(array, middle + 1, rightIndex);
				mergeArrays(array, leftIndex, middle + 1, rightIndex);
			}
		}
	}

	private void mergeArrays(T[] array, int left, int middle, int right) {
		List<T> a1 = new ArrayList<T>(Arrays.asList(Arrays.copyOfRange(array, left, middle)));
		List<T> a2 = new ArrayList<T>(Arrays.asList(Arrays.copyOfRange(array, middle, right + 1)));

		int i = left;
		while (a1.size() > 0 && a2.size() > 0) {
			T a = a1.get(0);
			T b = a2.get(0);

			if (a.compareTo(b) < 0) {
				array[i] = a1.remove(0);
			} else {
				array[i] = a2.remove(0);
			}
			i++;
		}
		while (!a1.isEmpty()) {
			array[i] = a1.remove(0);
			i++;
		}
		while (!a2.isEmpty()) {
			array[i] = a2.remove(0);
			i++;
		}
	}
}
