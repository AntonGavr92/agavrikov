package ru.job4j.additional;

import java.util.Random;

/**
 * Created by gavrikov.a on 28/08/2017.
 */
public class MaxVectorSearcher {

    /**
     * Fields.
     */
    private final Field[][] arr;


    /**
     * Constructor.
     * @param capacity size of arrays
     */
    public MaxVectorSearcher(int capacity) {
        this.arr = new Field[capacity][capacity];
        Random rand = new Random();
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++){
                this.arr[i][j] = new Field(rand.nextInt(2));
                System.out.print(this.arr[i][j].value);
            }
            System.out.println();
        }
    }

    public MaxVectorSearcher(Field[][] fields){
        this.arr = fields;
    }

    /**
     * Point of start programm.
     * @param args params
     */
    public static void main(String[] args) {
        MaxVectorSearcher mvs = new MaxVectorSearcher(5);
        System.out.print(String.format("%s - max vektor", mvs.findMaxVector()));

    }

    /**
     * Method for search max vector in arr.
     * @return max vector
     */
    public int findMaxVector() {
        /*
         * В данном случае, кажется можно было соптимизировать, до того что бы мы не проходили выборкой по проверенным полям,
         * используюя для хранения полей список и пробегаясь по нему. Но и при подобной схеме результат будет правильный.
         */
        int maxVector = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int countElVec = findValInColTop(1, 0, i, j) + findValInColBot(1, 0, i, j) - 1;
                int hor = findValInRowRight(1, 0, i, j) + findValInRowLeft(1, 0, i, j) - 1;
                if (countElVec > maxVector) {
                    maxVector = countElVec;
                }
                if (hor > maxVector) {
                    maxVector = hor;
                }
            }
        }
        return maxVector;
    }

    /**
     * Method for search elements from right.
     * @param value search value
     * @param counter counter (length vector)
     * @param row row
     * @param col col
     * @return length vector
     */
    private int findValInRowRight(int value, int counter, int row, int col) {
        if(this.arr[row][col].value != value){
            return counter;
        } else {
            counter++;
            if (col < this.arr[row].length - 1){
                counter = findValInRowRight(value, counter, row, col + 1);
            }
        }
        return counter;
    }

    /**
     * Method for search elements from left.
     * @param value search value
     * @param counter counter (length vector)
     * @param row row
     * @param col col
     * @return length vector
     */
    private int findValInRowLeft(int value, int counter, int row, int col) {
        if(this.arr[row][col].value != value){
            return counter;
        } else {
            counter++;
            if (col > 0){
                counter = findValInRowLeft(value, counter, row, col - 1);
            }
        }
        return counter;
    }

    /**
     * Method for search elements from top.
     * @param value search value
     * @param counter counter (length vector)
     * @param row row
     * @param col col
     * @return length vector
     */
    private int findValInColTop(int value, int counter, int row, int col) {
        if(this.arr[row][col].value != value){
            return counter;
        } else {
            counter++;
            if (row < this.arr.length - 1){
                counter = findValInColTop(value, counter, row + 1, col);
            }
        }
        return counter;
    }

    /**
     * Method for search elements from bottom.
     * @param value search value
     * @param counter counter (length vector)
     * @param row row
     * @param col col
     * @return length vector
     */
    private int findValInColBot(int value, int counter, int row, int col) {
        if(this.arr[row][col].value != value){
            return counter;
        } else {
            counter++;
            if (row > 0){
                counter = findValInColBot(value, counter, row - 1, col);
            }
        }
        return counter;
    }
}
