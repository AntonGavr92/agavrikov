package ru.job4j.set;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Класс реализующий структуру Set на массивах.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <E> - тип элементов в нашей структуре
 */
public class SimpleSet<E> implements Iterator<E> {

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
     * Текущий индекс для итератора.
     */
    private int currentIndex = 0;

    /**
     * Конструктор, принимающий параметр - первоначальный размер массива.
     * @param capacity первоначальный размер массива
     */
    public SimpleSet(int capacity) {
        this.container = new Object[capacity];
    }

    /**
     * Конструктор по умолчанию.
     */
    public SimpleSet() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Текущее количество элементов в set.
     * @return количество элементов в set
     */
    public int size() {
        return this.size;
    }

    /**
     * Метод, описывающий добавление в set.
     *
     * @param value объект, помещаемый в set.
     */
    public void add(E value) {
        boolean isDouble = false;
        for (int i = 0; i < this.size; i++) {
            E obj = (E) this.container[i];
            if (obj.equals(value)) {
                isDouble = true;
                break;
            }
        }
        if (!isDouble) {
            if (this.size < container.length) {
                this.container[size++] = value;
            } else {
                this.container = Arrays.copyOf(this.container, this.size * 3 / 2 + 1);
                this.container[size++] = value;
            }
        }
    }


    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return currentIndex < size;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     */
    @Override
    public E next() {
        return (E) container[currentIndex++];
    }
}
