package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test ArrayDuplicate class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
*/
public class ArrayDuplicateTest {
	/**
	 * Тест метода remove класса ArrayDuplicate масива с 6 элементами.
	*/
	@Test
	public void whenArrayDuplicateSmallThenRemoveDuplicateArray() {
		ArrayDuplicate arrayDuplicate = new ArrayDuplicate();

		String[] array = {"a", "b", "c", "c", "a", "d"};
		String[] result = arrayDuplicate.remove(array);
		String[] arrayExpected = {"a", "b", "c", "d"};
		assertThat(result, is(arrayExpected));
	}

	/**
	 * Тест метода remove класса ArrayDuplicate масива с 10 элементами и с дополнительным дублем.
	*/
	@Test
	public void whenArrayDuplicateBigThenRemoveDuplicateArray() {
		ArrayDuplicate arrayDuplicate = new ArrayDuplicate();

		String[] array = {"a", "b", "c", "c", "a", "d", "c", "e", "f", "g"};
		String[] result = arrayDuplicate.remove(array);
		String[] arrayExpected = {"a", "b", "c", "f", "g", "d", "e"};
		assertThat(result, is(arrayExpected));
	}
}