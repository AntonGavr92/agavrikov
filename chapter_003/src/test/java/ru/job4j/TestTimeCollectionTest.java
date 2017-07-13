package ru.job4j;

import org.junit.Test;

import java.util.*;


/**
 * Класс для тестирования.
 * @author agavrikov
 * @since 13.07.2017
 * @version 1
 */
public class TestTimeCollectionTest {
    /**
     * Тестирование метода добавления.
     */
    @Test
    public void add() {
        TestTimeCollection methods = new TestTimeCollection();
        List<String> linkedList = new LinkedList<String>();
        long timeStart = new Date().getTime();
        long timeEnd = methods.add(linkedList, 1000000);
        System.out.println(timeEnd - timeStart);

        List<String> arrayList = new ArrayList<String>();
        timeStart = new Date().getTime();
        timeEnd = methods.add(arrayList, 1000000);
        System.out.println(timeEnd - timeStart);

        Set<String> treeSet = new TreeSet<String>();
        timeStart = new Date().getTime();
        timeEnd = methods.add(treeSet, 1000000);
        System.out.println(timeEnd - timeStart);
    }

    /**
     * Тестирование метода удаления.
     */
    @Test
    public void delete() {
        TestTimeCollection methods = new TestTimeCollection();
        List<String> linkedList = new LinkedList<String>();
        methods.add(linkedList, 100000);
        long timeStart = new Date().getTime();
        long timeEnd = methods.delete(linkedList, 10000);
        System.out.println(timeEnd - timeStart);

        List<String> arrayList = new ArrayList<String>();
        methods.add(arrayList, 100000);
        timeStart = new Date().getTime();
        timeEnd = methods.delete(arrayList, 10000);
        System.out.println(timeEnd - timeStart);

        Set<String> treeSet = new TreeSet<String>();
        methods.add(treeSet, 100000);
        timeStart = new Date().getTime();
        timeEnd = methods.delete(treeSet, 10000);
        System.out.println(timeEnd - timeStart);
    }

}