package sorting.divideAndConquer.hybridMergesort;

import java.util.ArrayList;
import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de
 * forma
 * que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados.
 * E a cada chamada
 * interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 * INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if ((leftIndex < rightIndex) && (leftIndex >= 0) && (rightIndex <= array.length - 1) && (array.length != 0)) {
			MERGESORT_APPLICATIONS = 0;
			INSERTIONSORT_APPLICATIONS = 0;
			hybridMergeSort(array, leftIndex, rightIndex);
		}
	}

	private void hybridMergeSort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex != rightIndex) {
			if (rightIndex - leftIndex > SIZE_LIMIT) {
				int middle = (leftIndex + rightIndex) / 2;
				hybridMergeSort(array, leftIndex, middle);
				hybridMergeSort(array, middle + 1, rightIndex);
				mergeArrays(array, leftIndex, middle + 1, rightIndex);
				MERGESORT_APPLICATIONS++;
			}
			insertionSort(array, leftIndex, rightIndex);
		}
	}

	private void mergeArrays(T[] array, int left, int middle, int right) {
		ArrayList<T> a1 = new ArrayList<T>(Arrays.asList(Arrays.copyOfRange(array, left, middle)));
		ArrayList<T> a2 = new ArrayList<T>(Arrays.asList(Arrays.copyOfRange(array, middle, right + 1)));

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

	private void insertionSort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			int key = i;
			for (int j = i; j >= leftIndex; j--) {
				if (array[key].compareTo(array[j]) < 0) {
					Util.swap(array, j, key);
					key = j;
				}
			}
		}
		INSERTIONSORT_APPLICATIONS++;
	}
}
