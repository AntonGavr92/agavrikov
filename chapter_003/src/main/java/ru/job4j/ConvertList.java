package ru.job4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Класс для конвертации массива в ArrayList и наоборот.
 * @author agavrikov
 * @since 13.07.2017
 * @version 1
 */
public class ConvertList {

    /**
     * Метод для конвертирования массива в ArrayList.
     * @param array массив, который нужно конвертировать
     * @return преобразованный массив в ArrayList
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                result.add(array[i][j]);
            }
        }
        return result;
    }

    /**
     * Метод для конвертации ArrayList в массив.
     * @param list ArrayList, который необходимо преобразовать
     * @param rows количество строк в массиве
     * @return ArrayList
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int length = list.size() / rows;
        if (list.size() % rows != 0) {
            length++;
        }
        Iterator<Integer> iter = list.iterator();
        int[][] result = new int[rows][length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < length; j++) {
                if(iter.hasNext()) {
                    result[i][j] = iter.next();
                } else {
                    result[i][j] = 0;
                }

            }
        }
        return result;
    }

    /**
     * Метод который преобразует колекцию массивов в коллекцию чисел из всех массивов.
     * @param list - ArrayList содержаший в себе массивы
     * @return ArrayList содержащий все числа из ArrayList с массивами
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<Integer>();
        for (int[] val : list) {
            for (int i = 0; i < val.length; i++) {
                result.add(val[i]);
            }
        }
        return result;
    }
}
