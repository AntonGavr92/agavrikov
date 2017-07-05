package ru.job4j.loop;

/**
 * Class Класс для вычисления факториала по заданному числу.
 * @author agavrikov
 * @since 05.07.2017
 * @version 1
*/
public class Factorial {

	/**
	 * Метод вычисляет факториал.
	 * @param n - число для которого нужно вычислить факториал
	 * @return факториал числа
	*/
	public int calc(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}

}