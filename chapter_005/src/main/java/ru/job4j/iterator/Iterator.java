package ru.job4j.iterator;

/**
 * Класс реализующий работу итератора в массиве массивов.
 * @author agavrikov
 * @since 14.07.2017
 * @version 1
 */
public class Iterator {

    /**
     * Поле для хранения массива.
     */
    private int[][] array;

    /**
     * Текущий индекс строки.
     */
    private int currentIndexRow = 0;

    /**
     * Текущий индекс колонки.
     */
    private int currentIndexCol = 0;

    /**
     * Конструктор для инициализации полей.
     * @param array массив, по которому необходимо пройтись с помощью итератора
     */
    public Iterator(int[][] array) {
        this.array = array;
    }

    /**
     * Метод, для проверки наличия следующего элемента.
     * @return true если есть следующий элемент, false - если элементы кончились
     */
    public boolean hasNext() {
        return this.currentIndexRow < this.array.length && !(this.currentIndexRow == this.array.length - 1 && currentIndexCol == this.array[this.currentIndexRow].length);
    }

    /**
     * Метод для последовательного получения значений.
     * @return значение элемента
     */
    public int next() {
        if (this.currentIndexCol < this.array[currentIndexRow].length) {
            return this.array[this.currentIndexRow][this.currentIndexCol++];
        } else {
            this.currentIndexCol = 0;
            return this.array[++this.currentIndexRow][this.currentIndexCol++];
        }
    }

}
