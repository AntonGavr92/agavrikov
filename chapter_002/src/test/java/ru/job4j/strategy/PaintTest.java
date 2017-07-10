package ru.job4j.strategy;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Strategy package.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    /**
     * тестирование рисования треугольника.
     */
    @Test
    public void whenDrawTriangleThenTriangle() {
        Paint paint = new Paint();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        paint.draw(new Triangle());
        assertThat(out.toString(), is(String.format("   x   %s  xxx  %s xxxxx %sxxxxxxx%s", System.getProperty("line.separator"), System.getProperty("line.separator"), System.getProperty("line.separator"), System.getProperty("line.separator"))));

        /*StringBuilder pic = new StringBuilder();
        pic.append("   x   ");
        pic.append(System.lineSeparator());
        pic.append("  xxx  ");
        pic.append(System.lineSeparator());
        pic.append(" xxxxx ");
        pic.append(System.lineSeparator());
        pic.append("xxxxxxx");
        String extend = toString();
        assertThat(result, is(extend));*/
    }

    /**
     * тестирование рисования квадрата.
     */
    @Test
    public void whenDrawTriangleThenSquare() {
        Paint paint = new Paint();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        paint.draw(new Square());
        assertThat(out.toString(), is(String.format(" xxxx %s xxxx %s xxxx %s xxxx %s", System.getProperty("line.separator"), System.getProperty("line.separator"), System.getProperty("line.separator"), System.getProperty("line.separator"))));
    }

}