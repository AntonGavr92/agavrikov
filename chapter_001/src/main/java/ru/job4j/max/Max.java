package ru.job4j.max;

/**
 * Class Класс для вычисления наибольшего числа.
 * @author agavrikov
 * @since 04.07.2017
 * @version 1
*/
public class Max {

	/**
	 * Метод сравнения двух чисел и возвращающий набольшее число.
	 * @param first - первое число
	 * @param second - второе число
	 * @return наибольшее число из двух переданных чисел
	*/
	public int max(int first, int second) {
		return first > second ? first : second;
	}

}