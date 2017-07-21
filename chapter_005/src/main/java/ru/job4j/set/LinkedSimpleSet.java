package ru.job4j.set;

import java.util.Iterator;
import ru.job4j.list.MyLinkedList;

/**
 * Класс реализующий структуру Set на связном списке.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <E> - тип элементов в нашей структуре
 */
public class LinkedSimpleSet<E> extends MyLinkedList<E> implements Iterator<E> {

    /**
     * Текущий нод итератора.
     */
    private Node<E> currentNode;

    /**
     * Метод, описывающий добавление в set.
     *
     * @param value объект, помещаемый в set.
     */
    public void add(E value) {
        boolean isDouble = false;
        if (this.size > 0) {
            while (this.hasNext()) {
                if (value.equals(this.next())) {
                    isDouble = true;
                    break;
                }
            }
        }
        if (!isDouble) {
            super.add(value);
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
        if (currentNode == null && size > 0) {
            result = true;
        } else if (currentNode.getNext() != null) {
            result = true;
        }
        if (!result) {
            currentNode = first;
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
        E result = null;
        if (currentNode == null) {
            currentNode = first;
            result = first.getItem();
        } else {
            result = currentNode.getNext().getItem();
            currentNode = currentNode.getNext();
        }
        return result;
    }
}
