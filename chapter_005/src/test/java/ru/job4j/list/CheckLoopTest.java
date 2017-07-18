package ru.job4j.list;

import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test CheckLoop class.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 */
public class CheckLoopTest {

    /**
     * Метод для тестирования замыкания.
     */
    @Test
    public void checkStructure() {
        CheckLoop check = new CheckLoop();
        MyNode first = new MyNode(1);
        MyNode two = new MyNode(2);
        MyNode third = new MyNode(3);
        MyNode four = new MyNode(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        boolean result = check.hasCycle(first);
        boolean expected = true;
        assertThat(result, is(expected));
    }
}