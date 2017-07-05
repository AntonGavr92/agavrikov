package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;



/**
 * Test Paint class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
*/
public class PaintTest {

	/**
	 * Тест метода piramid класса Paint с высотой - 2 .
	*/
	@Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Paint paint = new Paint();
        String result = paint.piramid(2);
        final String line = System.getProperty("line.separator");
        String expected = String.format(" ^ %s^^^", line);
        assertThat(result, is(expected));
	}

	/**
	 * Тест метода piramid класса Paint с высотой - 3 .
	*/
	@Test
	public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        Paint paint = new Paint();
        String result = paint.piramid(3);
        final String line = System.getProperty("line.separator");
        String expected = String.format("  ^  %s ^^^ %s^^^^^", line, line);
        assertThat(result, is(expected));
    }

	/**
	 * Тест метода piramid класса Paint с высотой - 5 .
	*/
	@Test
	public void whenPaintBoardWithWidthTenAndHeightElevenThenStringWithTenColsAndElevenRows() {
        Paint paint = new Paint();
        String result = paint.piramid(5);
        final String line = System.getProperty("line.separator");
        String expected = String.format("    ^    %s   ^^^   %s  ^^^^^  %s ^^^^^^^ %s^^^^^^^^^", line, line, line, line);
        assertThat(result, is(expected));
    }
}