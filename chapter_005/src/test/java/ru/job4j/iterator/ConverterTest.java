package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Сonverter class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class ConverterTest {
    /**
     * Тестирование метода hasNext().
     */
    @Test
    public void testMethodHasNext() {
        Converter con = new Converter();
        List<Integer> test1 = new ArrayList<Integer>();
        List<Integer> test2 = new ArrayList<Integer>();
        test1.add(1);
        test1.add(2);
        test2.add(3);
        test2.add(4);
        Iterator<Iterator<Integer>> it = Arrays.asList(
                test1.iterator(),
                test2.iterator()
        ).iterator();
        Iterator<Integer> res = con.convert(it);
        int result = 0;
        while (res.hasNext()) {
            result = res.next();
        }
        int expected = 4;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода next().
     */
    @Test
    public void testMethodNext() {
        Converter con = new Converter();
        List<Integer> test1 = new ArrayList<Integer>();
        List<Integer> test2 = new ArrayList<Integer>();
        test1.add(1);
        test1.add(2);
        test2.add(3);
        test2.add(4);
        Iterator<Iterator<Integer>> it = Arrays.asList(
                test1.iterator(),
                test2.iterator()
        ).iterator();
        Iterator<Integer> res = con.convert(it);
        res.next();
        res.next();
        int result = res.next();

        int expected = 3;
        assertThat(result, is(expected));
    }
}