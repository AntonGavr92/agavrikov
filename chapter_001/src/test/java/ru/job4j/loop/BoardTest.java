package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;



/**
 * Test Board class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
*/
public class BoardTest {

	/**
	 * Тест метода paint класса Board с шириной доски 3 и высотой - 3 .
	*/
	@Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();
        String result = board.paint(3, 3);
        final String line = System.getProperty("line.separator");
        String expected = String.format("x x%s x %sx x%s", line, line, line);
        assertThat(result, is(expected));
	}

	/**
	 * Тест метода paint класса Board с шириной доски 5 и высотой - 4 .
	*/
	@Test
	public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        Board board = new Board();
        String result = board.paint(5, 4);
        final String line = System.getProperty("line.separator");
        String expected = String.format("x x x%s x x %sx x x%s x x %s", line, line, line, line);
        assertThat(result, is(expected));
    }

	/**
	 * Тест метода paint класса Board с шириной доски 10 и высотой - 6 .
	*/
	@Test
	public void whenPaintBoardWithWidthTenAndHeightElevenThenStringWithTenColsAndElevenRows() {
        Board board = new Board();
        String result = board.paint(10, 6);
        final String line = System.getProperty("line.separator");
        String expected = String.format("x x x x x %sx x x x x %sx x x x x %sx x x x x %sx x x x x %sx x x x x %s", line, line, line, line, line, line);
        assertThat(result, is(expected));
    }


	/**
	 * Тест метода paint класса Board с шириной доски 3 и высотой - 3 без вложенного цикла.
	*/
	@Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRowsWithoutInnerLoop() {
        Board board = new Board();
        String result = board.paintWithoutInnerLoop(3, 3);
        final String line = System.getProperty("line.separator");
        String expected = String.format("x x%s x %sx x%s", line, line, line);
        assertThat(result, is(expected));
	}

	/**
	 * Тест метода paint класса Board с шириной доски 5 и высотой - 4 без вложенного цикла.
	*/
	@Test
	public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRowsWithoutInnerLoop() {
        Board board = new Board();
        String result = board.paintWithoutInnerLoop(5, 4);
        final String line = System.getProperty("line.separator");
        String expected = String.format("x x x%s x x %sx x x%s x x %s", line, line, line, line);
        assertThat(result, is(expected));
    }

	/**
	 * Тест метода paint класса Board с шириной доски 10 и высотой - 6 без вложенного цикла.
	*/
	@Test
	public void whenPaintBoardWithWidthTenAndHeightElevenThenStringWithTenColsAndElevenRowsWithoutInnerLoop() {
        Board board = new Board();
        String result = board.paintWithoutInnerLoop(10, 6);
        final String line = System.getProperty("line.separator");
        String expected = String.format("x x x x x %sx x x x x %sx x x x x %sx x x x x %sx x x x x %sx x x x x %s", line, line, line, line, line, line);
        assertThat(result, is(expected));
    }
}