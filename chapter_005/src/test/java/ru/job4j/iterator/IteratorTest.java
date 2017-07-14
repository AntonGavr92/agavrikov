package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Iterator class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class IteratorTest {

    /**
     * Метод для тестирования метода next.
     */
    @Test
    public void testNext() {
        Iterator it = new Iterator(new int[][]{{1, 2}, {3, 4}});
        it.next();
        it.next();
        it.next();
        int result = it.next();
        int expected = 4;
        assertThat(result, is(expected));
    }

    /**
     * Метод для тестирования метода hasNext.
     */
    @Test
    public void whenHasNextIsTrueThanGetElement() {
        Iterator it = new Iterator(new int[][]{{1, 2}, {3, 4}});
        int result = 0;
        while (it.hasNext()) {
            result = it.next();
        }
        int expected = 4;
        assertThat(result, is(expected));
    }
}