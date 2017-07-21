package ru.job4j.list;

import java.util.Iterator;

/**
 * Класс реализующий структуру Stack.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <E> - тип элементов в нашей структуре
 */
public class MyQueue<E> extends MyLinkedList<E> {

    /**
     * Метод для добавления данных в Stack.
     * @param value ссылка на объект.
     */
    public void push(E value) {
        this.add(value);
    }

    /**
     * Метод для получения данных из Stack.
     * @return ссылка на объект.
     */
    public E pop() {
        E res = this.first.getItem();
        this.first = this.first.getNext();
        this.first.setPrev(null);
        this.size--;
        return res;
    }

    /**
     * Итератор.
     * @return итератор
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> currentNode;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (currentNode == null && size > 0) {
                    result = true;
                } else if (currentNode.getNext() != null) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                E result = null;
                if (currentNode == null) {
                    currentNode = last;
                    result = last.getItem();
                } else {
                    result = currentNode.getNext().getItem();
                    currentNode = currentNode.getNext();
                }
                return result;
            }
        };
    }

}
