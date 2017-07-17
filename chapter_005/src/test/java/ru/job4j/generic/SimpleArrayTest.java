package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test SimpleArray class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayTest {
    /**
     * Метод для проверки метода add.
     */
    @Test
    public void whenSimpleArrayAdd1ElementThenSimpleArrayHas1Element() {
        SimpleArray<Integer> arr = new SimpleArray<Integer>(10);
        arr.add(1);
        int result = arr.get(0);
        int expected = 1;
        assertThat(result, is(expected));
    }

    /**
     * Метод для проверки метода get.
     */
    @Test
    public void whenSimpleArrayAdd1ElementThenSimpleArrayIndex5IsNull() {
        SimpleArray<Integer> arr = new SimpleArray<Integer>(10);
        arr.add(1);
        Integer result = arr.get(5);
        Integer expected = null;
        assertThat(result, is(expected));
    }

    /**
     * Метод для проверки метода delete.
     */
    @Test
    public void whenSimpleArrayAdd1ElementAndDelete1ElemtnThenSimpleArrayIndex0IsNull() {
        SimpleArray<Integer> arr = new SimpleArray<Integer>(10);
        arr.add(1);
        arr.delete(0);
        Integer result = arr.get(0);
        Integer expected = null;
        assertThat(result, is(expected));
    }

    /**
     * Метод для проверки метода update.
     */
    @Test
    public void whenSimpleArrayAdd1ElementAndUpdateSameElementValue3ThenSimpleArrayGetByIndex1Is3() {
        SimpleArray<Integer> arr = new SimpleArray<Integer>(10);
        arr.add(1);
        arr.update(0, 3);
        Integer result = arr.get(0);
        Integer expected = 3;
        assertThat(result, is(expected));
    }
}