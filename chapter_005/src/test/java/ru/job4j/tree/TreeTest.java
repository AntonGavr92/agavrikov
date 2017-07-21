package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test tree class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {

    /**
     * Тестирование добавления одного элемента.
     */
    @Test
    public void testAdd1Element() {
        Tree<String> tree = new Tree<String>();
        boolean result = tree.add("Parent", "Child");
        boolean expected = true;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование добавления нескольких элементов элемента.
     */
    @Test
    public void testAddManyElements() {
        Tree<String> tree = new Tree<String>();
        tree.add("Parent", "Child");
        tree.add("Child", "Child1");

        tree.add("Child", "Test");
        tree.add("Test", "Child7");
        tree.add("Test", "Child8");

        tree.add("Child1", "Child2");
        tree.add("Child2", "Child3");
        tree.add("Child1", "Child4");
        tree.add("Child1", "Child5");
        tree.add("Child", "Child6");

        int result = tree.root.children.get(0).children.get(0).children.size();
        int expected = 3;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода isBinary.
     */
    @Test
    public void testisBinary() {
        Tree<String> tree = new Tree<String>();
        tree.add("Parent", "Child");
        tree.add("Child", "Child1");
        tree.add("Child2", "Child3");
        tree.add("Child1", "Child4");
        tree.add("Child1", "Child5");
        tree.add("Child", "Child6");

        boolean result = tree.isBinary();
        boolean expected = true;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование итератора.
     */
    @Test
    public void testForEach() {
        Tree<String> tree = new Tree<String>();
        tree.add("Parent", "Child");
        tree.add("Child", "Child1");
        tree.add("Child2", "Child3");
        tree.add("Child1", "Child4");
        tree.add("Child1", "Child5");
        tree.add("Child", "Child6");
        String result = "";
        for (String s : tree) {
            result = s;
        }

        String expected = "Parent";
        assertThat(result, is(expected));
    }


}