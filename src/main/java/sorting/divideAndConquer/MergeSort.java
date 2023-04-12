package sorting.divideAndConquer;

import java.lang.reflect.Array;
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
		if (leftIndex >= rightIndex) {
			return;
		}
		int middle = (int) Math.floor(leftIndex + rightIndex) / 2;
		sort(array, leftIndex, middle);
		sort(array, middle + 1, rightIndex);
		mergeArrays(array, leftIndex, middle, rightIndex);
	}

	private void mergeArrays(T[] array, int left, int middle, int right) {
		List<T> a1 = Arrays.asList(Arrays.copyOfRange(array, left, middle));
		List<T> a2 = Arrays.asList(Arrays.copyOfRange(array, middle + 1, right));

		int i = 0;
		while (!(a1.isEmpty() && a2.isEmpty())) {
			if (a1.get(0).compareTo(a2.get(0)) < 0) {
				array[i] = a1.get(0);
				a1.remove(0);
			} else {
				array[i] = a2.get(0);
				a2.remove(0);
			}
			i++;
		}
		while (!a1.isEmpty()) {
			array[i] = a1.get(0);
			a1.remove(0);
		}
		while (!a2.isEmpty()) {
			array[i] = a2.get(0);
			a2.remove(0);
		}
	}
}
