package ru.job4j.tree;

import java.util.ArrayList;
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
     * Поле для итератора в котором хранятся значения.
     */
    List<E> iterList = new ArrayList<>();

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
        if (node != null) {
            node.addChildren(child);
            result = true;
            iterList.add(parent);
        }
        return result;
    }

    /**
     * Метод для определения, является ли дерево бинарным.
     * @param node - узел
     * @return если дерево бинарное - true, иначе - false
     */
    public boolean isBinary(Node<E> node) {
        boolean isBinary = true;
        if (node.children.size() <= 2) {
            for (Node<E> childNode : node.children) {
                isBinary = isBinary(childNode);
                break;
            }
        } else {
            isBinary = false;
        }
        return isBinary;
    }

    /**
     * Перегрузка метода isBinary.
     * @return если дерево бинарное - true, иначе - false
     */
    public boolean isBinary() {
        return isBinary(this.root);
    }

    /**
     * Метод для нахождения искомого узла с помощью рекурсии.
     * @param node - узел в котором ищем
     * @param parent - родитель, которого ищем
     * @return возвращает null, если элемент не найден, иначе вернет ссылку на родительский узел.
     */
    public Node<E> findParent(Node<E> node, E parent) {
        Node<E> resNode = null;
        if (node.value.compareTo(parent) != 0) {
            if (node.children.size() > 0) {
                for (Node<E> childNode : node.children) {
                    resNode = findParent(childNode, parent);
                    break;
                }
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
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (iterList.size() > 0) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                E val = iterList.get(iterList.size() - 1);
                iterList.remove(iterList.size() - 1);
                return val;
            }
        };
    }
}