package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test point function.
*
* @author Anton Gavrikov
* @version $Id$
* @since 0.1
*/
public class PointTest {

	/**
	 * Test point true.
	*/
	@Test
	public void whenFirstNumMoreThanSecondNumThenFirstNum() {
		Point point = new Point(0, 5);
		boolean result = point.is(5, 5);
        boolean expected = true;
		assertThat(result, is(expected));
	}

	/**
	 * Test point false.
	*/
	@Test
	public void testFalseResultFunctionIs() {
		Point point = new Point(0, 5);
		boolean result = point.is(13, 28);
        boolean expected = false;
		assertThat(result, is(expected));
	}
}