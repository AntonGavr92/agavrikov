package ru.job4j.array;

/**
 * Class  класс для сортировки массива пузырьком.
 * @author agavrikov
 * @since 05.07.2017
 * @version 1
*/
public class BubbleSort {

	/**
	 * Метод сортирует массив.
	 * @param array - массив который необходимо отсортировать по возрастанию
	 * @return отсортированный массив
	*/
	public int[] sort(int[] array) {
		boolean needSort = true;
		int tmp = 0;
		while (needSort) {
			needSort = false;
			for (int i = 0; i < array.length; i++) {
				if (i + 1 !=  array.length) {
					if (array[i] > array[i + 1]) {
						tmp = array[i];
						array[i] = array[i + 1];
						array[i + 1] = tmp;
						needSort = true;
					}
				}
			}
		}
		return array;
	}

}