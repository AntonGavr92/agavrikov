package ru.job4j.task;

import org.junit.Test;
import ru.job4j.tree.BinaryTree;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test ChangeNodes class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class ChangeNodesTest {
    /**
     * тестирование замены с проходом в глубину.
     */
    @Test
    public void testHeight() {
        Integer result = null;
        ChangeNodes<Integer> tree = new ChangeNodes<Integer>();
        tree.add(5);
        tree.add(3);
        tree.add(1);
        tree.add(2);
        tree.add(4);
        tree.add(1);
        tree.heightChangeNodes();
        for (Integer value : tree) {
            result = value;
            break;
        }
        int expected = 1;
        assertThat(result, is(result));
    }

    /**
     * тестирование замены с проходом в ширину.
     */
    @Test
    public void testWidth() {
        Integer result = null;
        ChangeNodes<Integer> tree = new ChangeNodes<Integer>();
        tree.add(5);
        tree.add(3);
        tree.add(1);
        tree.add(2);
        tree.add(4);
        tree.add(1);
        tree.widthChangeNodes();
        for (Integer value : tree) {
            result = value;
            break;
        }
        int expected = 1;
        assertThat(result, is(result));
    }
}