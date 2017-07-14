package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test EvenIterator class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class EvenIteratorTest {
    /**
     * Метод для тестирования метода next.
     */
    @Test
    public void testNext() {
        EvenIterator it = new EvenIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        it.next();
        it.next();
        int result = it.next();
        int expected = 6;
        assertThat(result, is(expected));
    }

    /**
     * Метод для тестирования метода hasNext.
     */
    @Test
    public void whenHasNextIsTrueThanGetElement() {
        EvenIterator it = new EvenIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        int result = 0;
        while (it.hasNext()) {
            result = it.next();
        }
        int expected = 8;
        assertThat(result, is(expected));
    }
}