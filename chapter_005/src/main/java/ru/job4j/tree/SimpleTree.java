package ru.job4j.tree;

/**
 * Интерфейс описывающий элементарную структуру дерева.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <E> - Объект
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return true в случае успешного добавления иначе false.
     */
    boolean add(E parent, E child);
}
