package ru.job4j.array;

/**
 * Class  класс для поворота квадратного массива.
 * @author agavrikov
 * @since 05.07.2017
 * @version 1
*/
public class RotateArray {

	/**
	 * Метод поворачивает массив.
	 * @param array - массив который необходимо повернуть
	 * @return массив
	*/
	public int[][] rotate(int[][] array) {
		int[][] additionalArray = new int[array.length][array[0].length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				additionalArray[i][j] = array[array[array.length - 1 - i].length - 1 - j][i];
			}
		}
		return additionalArray;
	}

}