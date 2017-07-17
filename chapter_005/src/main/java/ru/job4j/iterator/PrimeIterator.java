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
        return getPrimeNumber(false) != -1 ? true : false;
    }

    /**
     * Метод для последовательного получения простых чисел в массиве.
     *
     * @return значение элемента
     */
    public int next() {
        return getPrimeNumber(true);
    }

    /**
     * Метод для нахождения простого числа в массиве.
     * @param needMoveCurrentElement - булев параметр, указывающий на необходимость сдвига каретки.
     * @return следующее просто число. Если таковое не найдено, вернет -1.
     */
    public int getPrimeNumber(boolean needMoveCurrentElement) {
        int res = -1;

        for (int i = this.currentIndex; i < array.length; i++) {
            boolean prime = true;
            for (int j = 2; j < this.array[i]; j++) {
                if (this.array[i] % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime && this.array[i] != 1) {
                if (needMoveCurrentElement) {
                    this.currentIndex++;
                }
                res = this.array[i];
                break;
            }
        }
        return res;
    }

}