package ru.job4j.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test OptimizedSimpleSet class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class OptimizedSimpleSetTest {
    /**
     * Тестирование метода добавления в set.
     */
    @Test
    public void whenAddElementToOptimizedSimpleSetThenSizeOptimizedSimpleSetIncrement() {
        OptimizedSimpleSet<String> set = new OptimizedSimpleSet<String>();
        set.add("Test");
        int result = set.size();
        int expected = 1;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода добавления в set 2 одиннаковых объектов.
     */
    @Test
    public void whenAddElementToOptimizedSimpleSetAndAddSameElementThenSizeOptimizedSimpleSetIncrement() {
        OptimizedSimpleSet<String> set = new OptimizedSimpleSet<String>();
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
    public void whenAdd1ElementToOptimizedSimpleSetAndStartForEachThenLastEmelementWillBeSecondAddedElement() {
        OptimizedSimpleSet<String> set = new OptimizedSimpleSet<String>();
        set.add("Test");
        set.add("Test foreach");
        String result = "";
        while (set.hasNext()) {
            result = set.next();
        }
        String expected = "Test foreach";
        assertThat(result, is(expected));
    }

    /**
     * Тестирование времени на вставку элементов.
     * Тест занял 0,022 сек
     */
    @Test
    public void timeTest() {
        OptimizedSimpleSet<Integer> set = new OptimizedSimpleSet<Integer>();
        for (int i = 0; i < 100000; i++) {
            set.add(i);
        }
    }
}