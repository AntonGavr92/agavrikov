package ru.job4j.set;

import java.util.Arrays;
import java.util.Iterator;
import ru.job4j.list.MyArrayList;

/**
 * Класс реализующий структуру Set на массивах.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <E> - тип элементов в нашей структуре
 */
public class SimpleSet<E> implements Iterator<E> {

    /**
     * Поле для хранения индекса итератора.
     */
    private int currentIndex = 0;

    /**
     * Поле для хранения списка на массиве.
     */
    private MyArrayList<E> list;


    /**
     * Конструктор, принимающий параметр - первоначальный размер массива.
     * @param capacity первоначальный размер массива
     */
    public SimpleSet(int capacity) {

        this.list = new MyArrayList<>(capacity);
    }

    /**
     * Конструктор по умолчанию.
     */
    public SimpleSet() {
        this.list = new MyArrayList<>();
    }

    /**
     * Текущее количество элементов в set.
     * @return количество элементов в set
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Метод, описывающий добавление в set.
     *
     * @param value объект, помещаемый в set.
     */
    public void add(E value) {
        boolean isDouble = false;
        for (E valList : this.list) {
            if (valList.equals(value)) {
                isDouble = true;
                break;
            }
        }
        if (!isDouble) {
            this.list.add(value);
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
        boolean result = false;
        if (currentIndex < this.list.size()){
            result = true;
        } else {
            currentIndex = 0;
        }
        return result;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     */
    @Override
    public E next() {
        return (E) this.list.get(currentIndex++);
    }
}
