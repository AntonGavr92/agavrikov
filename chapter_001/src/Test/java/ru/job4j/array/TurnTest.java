package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Turn class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
*/
public class TurnTest {
	/**
	 * Тест метода back класса Turn с четным количеством элементов.
	*/
	@Test
	public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
		Turn turn = new Turn();
		int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		int[] result = turn.back(array);
		int[] expected  = new int[]{8, 7, 6, 5, 4, 3, 2, 1};
		assertThat(result, is(expected));
	}

	/**
	 * Тест метода back класса Turn с нечетным количеством элементов.
	*/
	@Test
	public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
		Turn turn = new Turn();
		int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
		int[] result = turn.back(array);
		int[] expected  = new int[]{7, 6, 5, 4, 3, 2, 1};
		assertThat(result, is(expected));
	}

}