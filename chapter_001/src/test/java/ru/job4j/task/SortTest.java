package ru.job4j.task;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Sort class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class SortTest {

    /**
     * Test merge sort to array.
     */
    @Test
    public void mergeSort() {
        Sort sort = new Sort();
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] result = sort.mergeSort(array);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(result, is(expected));
    }

    /**
     * Test merge to arrays.
     */
    @Test
    public void mergeSortArrays() {
        Sort sort = new Sort();
        //int[] array1 = {6, 8, 10, 12};
        //int[] array2 = {1, 7, 9, 11, 13};
        int[] array2 = {6, 7};
        int[] array1 = {8, 9};

        int[] result = sort.mergeSortArrays(array1, array2);
        int[] expected = {6, 7, 8, 9};
        assertThat(result, is(expected));
    }

}