package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test max number class.
*
* @author Anton Gavrikov
* @version $Id$
* @since 0.1
*/
public class MaxTest {

	/**
	 * Test max number first argument more than second.
	*/

	@Test
	public void whenFirstNumMoreThanSecondNumThenFirstNum() {
		Max maxObj = new Max();
        int result = maxObj.max(15, 5);
        int expected = 15;
        assertThat(result, is(expected));
	}

	/**
	 * Test max number second argument more than first.
	*/

	@Test
	public void whenSecondNumMoreThanFirstNumThenSecondNum() {
		Max maxObj = new Max();
        int result = maxObj.max(5, 15);
        int expected = 15;
        assertThat(result, is(expected));
	}

	/**
	 * Test 3 numbers method.
	*/

	@Test
	public void whenThreeNumbersThenMaxNumber() {
		Max maxObj = new Max();
        int result = maxObj.max(5, 0, 9);
        int expected = 9;
        assertThat(result, is(expected));
	}
}