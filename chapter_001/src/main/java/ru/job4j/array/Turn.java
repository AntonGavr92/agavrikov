package ru.job4j.array;

/**
 * Class  класс для переворота массива.
 * @author agavrikov
 * @since 05.07.2017
 * @version 1
*/
public class Turn {

	/**
	 * Метод мен¤ет пор¤док элементов массива.
	 * @param array - массив который элементы которого нужно расположить в обратном порядке
	 * @return перевернутый массив
	*/
	public int[] back(int[] array) {
		int tmp = 0;
		for (int i = 0; i < array.length / 2; i++) {
			tmp = array[i];
			array[i] = array[array.length - 1 - i];
			array[array.length - 1 - i] = tmp;
		}
		return array;
	}

}