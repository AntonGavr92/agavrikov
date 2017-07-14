package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test EvenIterator class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class PrimeIteratorTest {
    /**
     * Метод для тестирования метода next.
     */
    @Test
    public void testNext() {
        PrimeIterator it = new PrimeIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        it.next();
        it.next();
        int result = it.next();
        int expected = 5;
        assertThat(result, is(expected));
    }

    /**
     * Метод для тестирования метода hasNext.
     */
    @Test
    public void whenHasNextIsTrueThanGetElement() {
        PrimeIterator it = new PrimeIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13});
        int result = 0;
        while (it.hasNext()) {
            result = it.next();
        }
        int expected = 13;
        assertThat(result, is(expected));
    }
}