package ru.job4j.set;

import java.util.Iterator;

/**
 * Класс реализующий структуру Set на связном списке.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <E> - тип элементов в нашей структуре
 */
public class LinkedSimpleSet<E> implements Iterator<E> {
    /**
     * Количество элементов в set.
     */
    protected int size = 0;

    /**
     * Текущий нод итератора.
     */
    private Node<E> currentNode;

    /**
     * Первый элемент в set.
     */
    protected Node<E> first;

    /**
     * Последний элемент в set.
     */
    protected Node<E> last;

    /**
     * Класс для описания одного элемента в нашем set.
     * @param <E> - тип элементов в нашей структуре
     */
    public static class Node<E> {

        /**
         * Объект данного нода.
         */
        private E item;

        /**
         * следующий нод.
         */
        private Node<E> next;

        /**
         * Предыдущий нод.
         */
        private Node<E> prev;

        /**
         * Конструктор.
         * @param prev - предыдущий нод
         * @param element - текущее значение
         * @param next - следующий нод
         */
        public Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Геттер текущего объека.
         * @return текущий объект.
         */
        public E getItem() {
            return this.item;
        }

        /**
         * Геттер следующего нода.
         * @return следующий нод.
         */
        public Node<E> getNext() {
            return this.next;
        }

        /**
         * Геттер следющего нода.
         * @return следущий нод.
         */
        public Node<E> getPrev() {
            return this.prev;
        }

        /**
         * Сеттер следющего нода.
         * @param node следущий нод.
         */
        public void setNext(Node<E> node) {
            this.next = node;
        }

    }
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
            if (this.first == null) {
                this.first = new Node<E>(null, value, null);
                this.last = this.first;
            } else {
                this.last = new Node<E>(this.last, value, null);
                if (this.last.getPrev() != null) {
                    this.last.getPrev().setNext(this.last);
                }
            }
            size++;
        }
    }

    /**
     * Получить количество элементов в контейнере.
     *
     * @return количество элементов в контейнере
     */
    public int size() {
        return this.size;
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
