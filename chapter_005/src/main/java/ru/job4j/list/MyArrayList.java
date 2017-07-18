package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Класс реализующий структуру списка на массиве.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <E> - тип элементов в нашей структуре
 */
public class MyArrayList<E> implements SimpleContainer<E> {

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
    public MyArrayList(int capacity) {
        this.container = new Object[capacity];
    }

    /**
     * Конструктор по умолчанию.
     */
    public MyArrayList() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Метод, описывающий добавление в список.
     *
     * @param value объект, помещаемый в список.
     */
    @Override
    public void add(E value) {
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
    @Override
    public E get(int index) {
        return index < this.size ? (E) this.container[index] : null;
    }

    /**
     * Получить количество элементов в контейнере.
     * @return количество элементов в контейнере
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                return (E) container[currentIndex++];
            }
        };
    }
}
