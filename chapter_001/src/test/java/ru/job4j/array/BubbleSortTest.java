package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test BubbleSort class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
*/
public class BubbleSortTest {
	/**
	 * Тест метода sort класса BubbleSort.
	*/
	@Test
	public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
		BubbleSort bubbleSort = new BubbleSort();
		int[] array = new int[]{-1, 4, 2, 25, 8, 3, 10, 0};
		int[] result = bubbleSort.sort(array);
		int[] expected  = new int[]{-1, 0, 2, 3, 4, 8, 10, 25};
		assertThat(result, is(expected));
	}
}