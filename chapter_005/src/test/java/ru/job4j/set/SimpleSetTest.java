package ru.job4j.set;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test SimpleSet class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class SimpleSetTest {
    /**
     * Тестирование метода добавления в set.
     */
    @Test
    public void whenAddElementToSimpleSetThenSizeSimpleSetIncrement() {
        SimpleSet<String> set = new SimpleSet<String>();
        set.add("Test");
        int result = set.size();
        int expected = 1;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода добавления в set 2 одиннаковых объектов.
     */
    @Test
    public void whenAddElementToSimpleSetAndAddSameElementThenSizeSimpleSetIncrement() {
        SimpleSet<String> set = new SimpleSet<String>();
        set.add("Test");
        set.add("Test");
        int result = set.size();
        int expected = 1;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование конструкции foreach.
     */
    @Test
    public void whenAdd1ElementToSimpleSetAndStartForEachThenLastEmelementWillBeSecondAddedElement() {
        SimpleSet<String> set = new SimpleSet<String>();
        set.add("Test");
        set.add("Test foreach");
        String result = "";
        while (set.hasNext()) {
            result = set.next();
        }
        String expected = "Test foreach";
        assertThat(result, is(expected));
    }
}