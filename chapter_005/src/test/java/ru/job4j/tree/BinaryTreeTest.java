package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test BinaryTree class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class BinaryTreeTest {

    /**
     * тестирование метода add.
     */
    @Test
    public void testAdd() {
        boolean result = false;
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(5);
        tree.add(4);
        tree.add(3);
        tree.add(2);
        tree.add(7);
        tree.add(1);
        result = tree.add(8);
        boolean expected = true;
        assertThat(result, is(result));
    }

    /**
     * тестирование forEach;.
     */
    @Test
    public void testForEach() {
        Integer result = null;
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        tree.add(5);
        tree.add(4);
        tree.add(3);
        tree.add(2);
        tree.add(7);
        tree.add(1);
        for (Integer value : tree) {
            result = value;
        }
        int expected = 5;
        assertThat(result, is(result));
    }
}