package ru.job4j.iterator;

/**
 * Класс реализующий работу итератора в массиве массивов.
 * @author agavrikov
 * @since 14.07.2017
 * @version 1
 */
public class PrimeIterator {

    /**
     * Поле для хранения массива.
     */
    private int[] array;

    /**
     * Текущий индекс строки.
     */
    private int currentIndex = 0;


    /**
     * Конструктор для инициализации полей.
     *
     * @param array массив, по которому необходимо пройтись с помощью итератора
     */
    public PrimeIterator(int[] array) {
        this.array = array;
    }

    /**
     * Метод, для проверки наличия следующего четного элемента.
     *
     * @return true если есть следующий элемент, false - если элементы кончились
     */
    public boolean hasNext() {
        for (int i = this.currentIndex; i < array.length; i++) {
            boolean prime = true;
            for (int j = 2; j < this.array[i]; j++) {
                if (this.array[i] % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime && this.array[i] != 1) {
                this.currentIndex = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Метод для последовательного получения четных значений.
     *
     * @return значение элемента
     */
    public int next() {
        for (int i = this.currentIndex; i < array.length; i++) {
            boolean prime = true;
            for (int j = 2; j < this.array[i]; j++) {
                if (this.array[i] % j == 0) {
                    prime = false;
                    this.currentIndex++;
                    break;
                }
            }
            if (prime && this.array[i] != 1) {
                this.currentIndex++;
                return this.array[i];
            }
            this.currentIndex++;
        }
        return -1;
    }
}