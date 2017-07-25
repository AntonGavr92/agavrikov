package ru.job4j.monitore;


import java.util.Arrays;

/**
 * Класс реализующий потокобезопасную структуру списка на массиве.
 * @author agavrikov
 * @since 25.07.2017
 * @version 1
 * @param <E> - тип элементов в нашей структуре
 */
public class ThreadSafeArrayList<E> {
    /**
     * Массив объектов.
     */
    private Object[] container;

    /**
     * Размер первоначального массива по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Количество элементов(не null) в списке.
     */
    private int size;

    /**
     * Конструктор, принимающий параметр - первоначальный размер массива.
     * @param capacity первоначальный размер массива
     */
    public ThreadSafeArrayList(int capacity) {
        this.container = new Object[capacity];
    }

    /**
     * Конструктор по умолчанию.
     */
    public  ThreadSafeArrayList() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Метод, описывающий добавление в список.
     *
     * @param value объект, помещаемый в список.
     */
    public synchronized void add(E value) {
        if (this.size < container.length) {
            this.container[size++] = value;
        } else {
            this.container = Arrays.copyOf(this.container, this.size * 3 / 2 + 1);
            this.container[size++] = value;
        }
    }

    /**
     * Метод, для получения объекта по индексу.
     *
     * @param index индекс элемента в списке.
     * @return объект.
     */

    public synchronized E get(int index) {
        return index < this.size ? (E) this.container[index] : null;
    }

    /**
     * Получить количество элементов в контейнере.
     * @return количество элементов в контейнере
     */

    public synchronized int size() {
        return this.size;
    }
}
