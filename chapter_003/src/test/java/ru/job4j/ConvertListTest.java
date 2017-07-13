package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class ConvertList.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class ConvertListTest {
    /**
     * Тестирование метода для конвертации массива в ArrayList.
     */
    @Test
    public void whenConvertList9ElementsThenListSize9Elements() {
        ConvertList convertList = new ConvertList();
        int[][] array = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> resultList = convertList.toList(array);
        int result = resultList.size();
        int expected = 9;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода для конвертации массива в ArrayList.
     */
    @Test
    public void whenConvertListAndLastElement9ThenListLastElement9() {
        ConvertList convertList = new ConvertList();
        int[][] array = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> resultList = convertList.toList(array);
        int result = resultList.get(resultList.size() - 1);
        int expected = 9;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода для конвертации ArrayList в массив.
     */
    @Test
    public void whenListSize9AndConvertListToArrayThenCountElementsResultArray9() {
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(i);
        }
        int[][] resultArray = convertList.toArray(list, 3);
        int result = resultArray.length * resultArray[0].length;
        int expected = list.size();
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода для конвертации ArrayList в массив.
     */
    @Test
    public void whenListSize10AndConvertListToArrayThenCountElementsResultArray12() {
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        int[][] resultArray = convertList.toArray(list, 3);
        int result = resultArray.length * resultArray[0].length;
        int expected = 12;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода для конвертации ArrayList в массив.
     */
    @Test
    public void whenListSize10AndConvertListToArrayThenLastElementIs0() {
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        int[][] resultArray = convertList.toArray(list, 3);
        int result = resultArray[resultArray.length - 1][resultArray[0].length - 1];
        int expected = 0;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода convert.
     */
    @Test
    public void testConvert() {
        ConvertList convertList = new ConvertList();
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2, 3});
        list.add(new int[]{3, 4, 5});
        list.add(new int[]{6, 7, 8, 9});
        List<Integer> resultList = convertList.convert(list);
        int result = resultList.get(resultList.size() - 1);
        int expected = 9;
        assertThat(result, is(expected));
    }

}