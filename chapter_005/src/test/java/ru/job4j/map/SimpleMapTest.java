package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test SimpleMap class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class SimpleMapTest {

    /**
     * Тестирование метода insert.
     */
    @Test
    public void whenAddElementToSimpleMapAndGetSameElementThenSameElement() {
        SimpleMap<String, String> map = new SimpleMap<String, String>(2);
        map.insert("Test", "Text");
        String result = map.get("Test");
        String expected = "Text";
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода get.
     */
    @Test
    public void whenAdd2ElementToSimpleMapAndGet1ElementThen1ElementValue() {
        SimpleMap<String, String> map = new SimpleMap<String, String>();
        map.insert("Test", "Text");
        map.insert("Another", "Text2");
        String result = map.get("Test");
        String expected = "Text";
        assertThat(result, is(expected));
    }


    /**
     * Тестирование метода delete.
     */
    @Test
    public void whenAdd2ElementToSimpleMapAndDelete1ElementThen1ElementNull() {
        SimpleMap<String, String> map = new SimpleMap<String, String>();
        map.insert("Test", "Text");
        map.insert("Another", "Text2");
        map.delete("Test");
        String result = map.get("Test");
        String expected = null;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование итератора.
     */
    @Test
    public void testIterator() {
        SimpleMap<String, String> map = new SimpleMap<String, String>();
        map.insert("Test", "Text");
        map.insert("Another", "Text2");
        map.insert("SameEl", "Text3");
        int counter = 0;
        while (map.hasNext()) {
            map.next();
            counter++;
        }
        map.delete("Test");
        int result = counter;
        int expected = 3;
        assertThat(result, is(expected));
    }
}