package ru.job4j.array;

import java.util.Arrays;

/**
 * Class  класс для удаления дублей из массива.
 * @author agavrikov
 * @since 05.07.2017
 * @version 1
*/
public class ArrayDuplicate {

	/**
	 * Метод удаляет дублирующие значения в массиве.
	 * @param array - массив из которого необходимо удалить дубли
	 * @return массив
	*/
	public String[] remove(String[] array) {
		int countDoubles = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (i != j && array[i] != null && array[i].equals(array[j])) {
					//решил остановиться на данной реализации, хотя если нам важно сохранить порядок, тогда нужно использовать еще один цикл в котором осуществлять сдвиг части массива влево
					array[j] = array[array.length - 1 - countDoubles];
					String tmp = array[array.length - 1 - countDoubles];
					array[array.length - 1 - countDoubles] = null;
					countDoubles++;
				}
			}
		}
		return Arrays.copyOf(array, array.length - countDoubles);
	}

}