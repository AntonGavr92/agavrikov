package ru.job4j.set;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test LinkedSimpleSet class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class LinkedSimpleSetTest {
    /**
     * Тестирование метода добавления в set.
     */
    @Test
    public void whenAddElementToSimpleSetThenSizeSimpleSetIncrement() {
        LinkedSimpleSet<String> set = new LinkedSimpleSet<String>();
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
        LinkedSimpleSet<String> set = new LinkedSimpleSet<String>();
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
        LinkedSimpleSet<String> set = new LinkedSimpleSet<String>();
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