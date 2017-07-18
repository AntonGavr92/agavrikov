package ru.job4j.list;

/**
 * Класс для создания связанного списка.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <T> - тип элементов в нашей структуре
 */
public class MyNode<T> {
    /**
     * значение.
     */
    T value;
    /**
     * следующий элемент.
     */
    MyNode<T> next;

    /**
     * Конструктор.
     * @param i - значение
     */
    public MyNode(T i) {
        this.value = i;
    }
}
