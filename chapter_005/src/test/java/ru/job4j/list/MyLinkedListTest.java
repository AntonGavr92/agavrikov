package ru.job4j.list;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test MyLinkedList class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class MyLinkedListTest {

    /**
     * Тестирование метода добавления в массив.
     */
    @Test
    public void whenAddElementToMyArrayListThenSizeMyArrayListIncrement() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add("Test");
        int result = list.size();
        int expected = 1;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода получения элемента по индексу.
     */
    @Test
    public void whenAddElementToMyArrayListAndGetElementWithIndexThenGetElementWithSameIndex() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add("Test");
        list.add("Test1");
        list.add("Test2");

        String result = list.get(1);
        String expected = "Test1";
        assertThat(result, is(expected));
    }

    /**
     * Тестирование конструкции foreach.
     */
    @Test
    public void whenAdd1ElementToMyArrayListAndStartForEachThenLastEmelementWillBeSecondAddedElement() {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add("Test");
        list.add("Test foreach");
        String result = "";
        for (String val : list) {
            result = val;
        }
        String expected = "Test foreach";
        assertThat(result, is(expected));
    }

}