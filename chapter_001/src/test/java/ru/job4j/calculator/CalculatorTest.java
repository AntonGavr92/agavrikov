package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test simple calculator.
*
* @author Anton Gavrikov
* @version $Id$
* @since 0.1
*/
public class CalculatorTest {
	/**
	 * Test add.
	*/
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

	/**
	 * Test substruct.
	*/
	@Test
    public void whenAddThreeMinusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.substruct(3D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

	/**
	 * Test div.
	*/
	@Test
    public void whenAddTenDividedTwoThenFive() {
        Calculator calc = new Calculator();
        calc.div(10D, 2D);
        double result = calc.getResult();
        double expected = 5D;
        assertThat(result, is(expected));
    }

	/**
	 * Test multiple.
	*/
	@Test
    public void whenAddTwoTimesThreeThenSix() {
        Calculator calc = new Calculator();
        calc.multiple(2D, 3D);
        double result = calc.getResult();
        double expected = 6D;
        assertThat(result, is(expected));
    }
}