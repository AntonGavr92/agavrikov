package ru.job4j.list;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test MyStack class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class MyStackTest {

    /**
     * Тестирование метода добавления в Stack.
     */
    @Test
    public void whenAddElementToMyArrayListThenSizeMyArrayListIncrement() {
        MyStack<String> list = new MyStack<String>();
        list.push("Test");
        int result = list.size();
        int expected = 1;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода получения элемента.
     */
    @Test
    public void whenAddElementToMyArrayListAndGetElementWithIndexThenGetElementWithSameIndex() {
        MyStack<String> list = new MyStack<String>();
        list.push("Test");
        list.push("Test1");
        list.push("Test2");

        String result = list.pop();
        String expected = "Test2";
        assertThat(result, is(expected));
    }

    /**
     * Тестирование конструкции foreach.
     */
    @Test
    public void whenAdd1ElementToMyArrayListAndStartForEachThenLastEmelementWillBeSecondAddedElement() {
        MyStack<String> list = new MyStack<String>();
        list.push("Test");
        list.push("Test foreach");
        String result = "";
        for (String val : list) {
            result = val;
        }
        String expected = "Test";
        assertThat(result, is(expected));
    }

}