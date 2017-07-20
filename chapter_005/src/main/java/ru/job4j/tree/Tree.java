package ru.job4j.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс реализующий дерево.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <E> - Ключ
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * поле для хранения структуры.
     */
     Node<E> root;

    /**
     * Класс описывающий узел дерева.
     * @param <E> объект
     */
    class Node<E> {
        /**
         * поле для хранения дочерних элементов.
         */
        List<Node<E>> children;

        /**
         * поле для хранения значения.
         */
        E value;

        /**
         * Конструктор.
         * @param value значение
         */
        Node(E value) {
            this.value = value;
            this.children = new LinkedList<Node<E>>();
        }

        /**
         * Мето для добавления дочернего узла.
         * @param value значение
         */
        public void addChildren(E value) {
            this.children.add(new Node<>(value));
        }
    }

    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     *
     * @param parent parent.
     * @param child  child.
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (this.root == null) {
            this.root = new Node<E>(parent);
            result = true;
        }
        Node<E> node = findParent(this.root, parent);
        node.addChildren(child);
        return result;
    }

    /**
     * Метод для нахождения искомого узла с помощью рекурсии.
     * @param node - узел в котором ищем
     * @param parent - родитель, которого ищем
     * @return возвращает null, если элемент не найден, иначе вернет ссылку на родительский узел.
     */
    public Node<E> findParent(Node<E> node, E parent) {
        Node<E> resNode = null;
        if (node.children.size() > 0 && node.value.compareTo(parent) != 0) {
            for (Node<E> childNode : node.children) {
                resNode = findParent(childNode, parent);
                break;
            }
        } else {
            resNode = node;
        }
        return resNode;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }
}