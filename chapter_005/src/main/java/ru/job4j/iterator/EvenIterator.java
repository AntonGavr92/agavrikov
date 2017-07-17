package ru.job4j.iterator;

/**
 * Класс реализующий работу итератора в массиве массивов.
 * @author agavrikov
 * @since 14.07.2017
 * @version 1
 */
public class EvenIterator {

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
    public EvenIterator(int[] array) {
        this.array = array;
    }

    /**
     * Метод, для проверки наличия следующего четного элемента.
     *
     * @return true если есть следующий элемент, false - если элементы кончились
     */
    public boolean hasNext() {
        boolean res = false;
        for (int i = this.currentIndex; i < array.length; i++) {
            if (this.array[i] % 2 == 0) {
                res = true;
            }
        }
        return res;
    }

    /**
     * Метод для последовательного получения четных значений.
     *
     * @return значение элемента
     */
    public int next() {
        int res = -1;
        for (int i = this.currentIndex; i < array.length; i++) {
            if (this.array[i] % 2 == 0) {
                this.currentIndex++;
                res = this.array[i];
                break;
            }
            this.currentIndex++;
        }
        return res;
    }
}
