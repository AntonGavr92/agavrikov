package ru.job4j.set;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Класс реализующий структуру Set на массивах, c использованием бинарного поиска для оптимизации вставки элемента.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <E> - тип элементов в нашей структуре
 */
public class OptimizedSimpleSet<E> implements Iterator<E> {
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
    public OptimizedSimpleSet(int capacity) {
        this.container = new Object[capacity];
    }

    /**
     * Конструктор по умолчанию.
     */
    public OptimizedSimpleSet() {
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
        int index = 0;
        if (this.size == 0) {
            this.container[this.size++] = value;
        } else {
            if (this.size == container.length){
                this.container = Arrays.copyOf(this.container, this.size * 3 / 2 + 1);
            }
            index = getIndexByHashCode(value.hashCode());
            if(index >= 0) {
                for (int i = this.size - 1; i >= index; i--) {
                    this.container[i + 1] = this.container[i];
                }
                this.container[index] = value;
                this.size++;
            }
        }
    }



    private int getIndexByHashCode(int hashCode) {
        int result;
        int left = 0;
        int right = this.size;
        while (right > left) {
            int mid = left + (right - left) / 2;
            if(container[mid].hashCode() < hashCode) {
                result = left;
                left = mid;
            } else if (container[mid].hashCode() > hashCode) {
                result = right;
                right = mid;
            } else {
                right = -1;
                break;
            }
            if (result == mid) {
                break;
            }
        }
        return right;
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
