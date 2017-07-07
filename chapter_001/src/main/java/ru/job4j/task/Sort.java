package ru.job4j.task;

import java.util.Arrays;

/**
 * Class Класс для реализации сортировок.
 * @author agavrikov
 * @since 07.07.2017
 * @version 1
 */

public class Sort {

    /**
     * Метод для сортировки массива слиянием. Решил реализовать и его.
     *
     * @param array - массив, который нужно отсортировать
     * @return отсортированный массив array
     */
    public int[] mergeSort(int[] array) {
        int len = array.length; //получаем длинну массива
        if (len < 2) {
            return array; // останавливаем рекурсию
        }

        int middle = len / 2;

        int[] array1 = mergeSort(Arrays.copyOfRange(array, 0, middle));
        int[] array2 = mergeSort(Arrays.copyOfRange(array, middle, len));

        return mergeSortArrays(array1, array2);
    }

    /**
     * Метод для слияния двух отсортированных массивов в 1 отсортированный.
     *
     * @param array1 - первый массив
     * @param array2 - второй массив
     * @return слитый отсортированный массив
     */
    public int[] mergeSortArrays(int[] array1, int[] array2) {
        int countElements = array1.length + array2.length;
        int[] resultArray = new int[countElements];
        int curIndexArray1 = 0;
        int curIndexArray2 = 0;



        for (int i = 0; i < countElements; i++) {
            if (curIndexArray1 < array1.length && curIndexArray2 < array2.length) {
                if (array1[curIndexArray1] < array2[curIndexArray2]) {
                    resultArray[i] = array1[curIndexArray1];
                    curIndexArray1++;
                } else {
                    resultArray[i] = array2[curIndexArray2];
                    curIndexArray2++;
                }
            } else if (curIndexArray2 < array2.length) {
                resultArray[i] = array2[curIndexArray2];
                curIndexArray2++;
            } else {
                resultArray[i] = array1[curIndexArray1];
                curIndexArray1++;
            }
        }

            /* первоначальный вариант, не взлете на массивах 6, 7 и 8, 9, тк как был предусмотрен лишь последний элемент
            if (i == resultArray.length - 1) { //Предусматриваем последний элемент
                if (array1[curIndexArray1] < array2[curIndexArray2]) {
                    resultArray[i] = array2[curIndexArray2];
                } else {
                    resultArray[i] = array1[curIndexArray1];
                }
            } else if (array1[curIndexArray1] < array2[curIndexArray2]) { //Сравниваем
                resultArray[i] = array1[curIndexArray1];
                if (curIndexArray1 + 1 != array1.length) { //контроль инкремента
                    curIndexArray1++;
                }

            } else if (array1[curIndexArray1] > array2[curIndexArray2]) {
                resultArray[i] = array2[curIndexArray2];
                if (curIndexArray2 + 1 != array2.length) {
                    curIndexArray2++;
                }
            }
        }
        */

        return resultArray;
    }
}
