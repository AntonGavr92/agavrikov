package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Factorial class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
*/
public class FactorialTest {
	/**
	 * Тест метода calc класса Counter с параметром 5.
	*/
	@Test
	public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(5);
		int expected  = 120;
		assertThat(result, is(expected));
	}

	/**
	 * Тест метода calc класса Counter с параметром 0.
	*/
	@Test
	public void whenCalculateFactorialForZeroThenOne() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(0);
		int expected  = 1;
		assertThat(result, is(expected));
	}

}