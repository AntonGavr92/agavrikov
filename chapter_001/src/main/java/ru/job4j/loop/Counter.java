package ru.job4j.loop;

/**
 * Class Класс для подсчета суммы четных чисел в заданном интервале.
 * @author agavrikov
 * @since 05.07.2017
 * @version 1
*/
public class Counter {

	/**
	 * Метод суммирует все четные числа в заданном интервале.
	 * @param start - левая граница интервала
	 * @param finish - правая граница интервала
	 * @return сумму четных чисел в заданном интервале
	*/
	public int add(int start, int finish) {
		int counter = 0;
		for (int i = start; i <= finish; i++) {
			if (i % 2 == 0) {
				counter += i;
			}
		}
		return counter;
	}

}