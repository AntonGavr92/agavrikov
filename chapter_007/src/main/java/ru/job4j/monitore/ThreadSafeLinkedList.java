package ru.job4j.monitore;

/**
 * Класс реализующий потокобезопасный связный список.
 * @author agavrikov
 * @since 25.07.2017
 * @version 1
 * @param <E> - тип элементов в нашей структуре
 */
public class ThreadSafeLinkedList<E> {
    /**
     * Количество элементов в списке.
     */
    protected int size = 0;

    /**
     * Первый элемент в списке.
     */
    protected Node<E> first;

    /**
     * Последний элемент в списке.
     */
    protected Node<E> last;

    /**
     * Класс для описания одного элемента в нашем листе.
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

        /**
         * Сеттер предыдущего нода.
         * @param node предыдущий нод.
         */
        public void setPrev(Node<E> node) {
            this.prev = node;
        }

    }
    /**
     * Метод, описывающий добавление в список.
     *
     * @param value объект, помещаемый в список.
     */
    public synchronized void add(E value) {
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

    /**
     * Метод, для получения объекта по индексу.
     *
     * @param index индекс элемента в списке.
     * @return объект.
     */

    public synchronized E get(int index) {
        E result = null;
        if (index < size && index < size / 2) {
            Node<E> resNode = this.first;
            for (int i = 0; i <= index; i++) {
                resNode = resNode.getNext();
            }
            result = resNode.getItem();
        } else if (index < size) {
            Node<E> resNode = this.last;
            for (int i = size - 1; i > index; i--) {
                resNode = resNode.getPrev();
            }
            result = resNode.getItem();
        }
        return result;
    }

    /**
     * Получить количество элементов в контейнере.
     *
     * @return количество элементов в контейнере
     */
    public synchronized int size() {
        return this.size;
    }
}
