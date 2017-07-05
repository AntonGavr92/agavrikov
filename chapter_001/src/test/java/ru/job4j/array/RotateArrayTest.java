package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test RotateArray class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
*/
public class RotateArrayTest {
	/**
	 * Тест метода rotate класса RotateArray массива 3х3.
	*/
	@Test
	public void whenRotateArray3x3ThenRotateArray() {
		RotateArray rotateArray = new RotateArray();

		int[][] array = new int[][]{{1, 2, 4},
								    {3, 4, 6},
									{9, 5, 8}};
		int[][] result = rotateArray.rotate(array);


		int[][] arrayExpected = new int[][]{{9, 3, 1},
											{5, 4, 2},
											{8, 6, 4}};
		assertThat(result, is(arrayExpected));
	}

	/**
	 * Тест метода rotate класса RotateArray массива 2х2.
	*/
	@Test
	public void whenRotateArray2x2ThenRotateArray() {
		RotateArray rotateArray = new RotateArray();

		int[][] array = new int[][]{{1, 2},
								    {3, 4}};
		int[][] result = rotateArray.rotate(array);


		int[][] arrayExpected = new int[][]{{3, 1},
											{4, 2}};
		assertThat(result, is(arrayExpected));
	}
}