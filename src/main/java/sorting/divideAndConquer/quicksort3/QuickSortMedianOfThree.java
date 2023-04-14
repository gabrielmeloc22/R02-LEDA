package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do
 * intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até
 * A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do
 * pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex || array.length == 0) {
			return;
		}
		medianOfThree(array, leftIndex, rightIndex);
		int pivot = partition(array, leftIndex, rightIndex);
		sort(array, leftIndex, pivot);
		sort(array, pivot + 1, rightIndex);
	}

	private int partition(T[] array, int left, int right) {
		T pivot = array[right - 1];
		int i = left;

		for (int j = left + 1; j <= right; j++) {
			if (array[j].compareTo(pivot) < 0) {
				i++;
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, i, left);

		return i;
	}

	private void medianOfThree(T[] array, int left, int right) {
		int middle = (left + right) / 2;
		T a = array[left];
		T b = array[middle];
		T c = array[right];

		if (a.compareTo(b) > 0) {
			Util.swap(array, left, middle);
		}

		if (a.compareTo(c) > 0) {
			Util.swap(array, left, right);
		}

		if (b.compareTo(c) > 0) {
			Util.swap(array, middle, right);
		}

		Util.swap(array, middle, right - 1);
	}
}

// [8, 9, 1, 5, 6, 4, 3]
// [3, 9, 1, 5, 6, 4, 8]
// [3, 9, 1, 4, 6, 5, 8]