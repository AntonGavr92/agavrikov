package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * Класс реализующий справочник.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <T> - Ключ
 * @param <V> - Значение
 */
public class SimpleMap<T, V> implements Iterator<T> {
    /**
     * Массив объектов.
     */
    private Object[] container;

    /**
     * Размер первоначального массива по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Количество элементов(не null) в справочнике.
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
    public SimpleMap(int capacity) {
        this.container = new Object[capacity];
    }

    /**
     * Конструктор по умолчанию.
     */
    public SimpleMap() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Метод для помещения пары ключ - значение в структуру.
     * @param key ключ
     * @param value значение
     * @return в случае успешного добавления true, иначе false
     */
    public boolean insert(T key, V value) {
        boolean result = true;
        if (this.size >= this.container.length - 1) {
            this.resize(this.size * 2);
        }
        int index = indexFor(Objects.hash(key), this.container.length);
        if (container[index] != null) {
            result = false;
        }  else {
            container[index] = value;
            this.size++;
        }
        return result;
    }

    /**
     * Вспомогательный метод, для увеличения размеров хеш таблицы.
     * @param capacity - новый размер
     */
    public void resize(int capacity) {
        this.container = Arrays.copyOf(this.container, capacity);
        Object[] arr = Arrays.copyOf(this.container, this.container.length);
        this.size = 0;
        for (int i = 0; i < arr.length; i++) {
            int hash = Objects.hash(arr[i]);
            int index = indexFor(hash, this.container.length);
            if (arr[i] != null) {
                if (index != i) {
                    this.container[index] = arr[i];
                    this.container[i] = null;
                }
                this.size++;
            }
        }
    }

    /**
     * Метод для получения значения по ключу.
     * @param key ключ
     * @return значение
     */
    public V get(T key) {
        int index = indexFor(Objects.hash(key), this.container.length);
        return (V) container[index];
    }

    /**
     * Метод для удаления значения по ключу.
     * @param key ключ
     * @return в случае успешного удаления true, иначе false
     */
    public boolean delete(T key) {
        boolean result = true;
        int index = indexFor(Objects.hash(key), this.container.length);
        container[index] = null;
        this.size--;
        return result;
    }

    /**
     * Вспомогательный метод для поиска индекса в хеш таблице с помошью хеша.
     * @param hash хеш
     * @param tableLength размер таблицы
     * @return индекс в хеш таблице
     */
    public int indexFor(int hash, int tableLength) {
        return hash & (tableLength - 1);
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
        boolean result = false;
        for (int i = this.currentIndex; i < this.container.length; i++) {
            if (this.container[i] != null) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     */
    @Override
    public T next() {
        T result = null;
        for (int i = this.currentIndex; i < this.container.length; i++) {
            if (this.container[i] != null) {
                result = (T) this.container[i];
                this.currentIndex = i + 1;
                break;
            }
        }
        return result;
    }
}
