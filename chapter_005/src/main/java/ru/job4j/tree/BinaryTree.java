package ru.job4j.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Класс реализующий бинарное дерево.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <E> - объект
 */
public class BinaryTree<E extends Comparable<E>> implements Iterable<E> {


    /**
     * Поле для итератора в котором хранятся значения.
     */
    List<E> iterList = new ArrayList<>();

    /**
     * Поле для хранения флага о том что iterList был пройден.
     */
    boolean iterListIsEnd = true;

    /**
     * поле для хранения корня.
     */
    Node<E> root;
    /**
     * Класс описывающий узел дерева.
     * @param <E> объект
     */
    class Node<E> {
        /**
         * поле для хранения ссылки на правый узел.
         */
        Node<E> right;

        /**
         * поле для хранения ссылки на левый узел.
         */
        Node<E> left;

        /**
         * поле для хранения значения.
         */
        E value;

        /**
         * Конструктор.
         * @param value - значение.
         */
        Node(E value) {
           this.value = value;
        }
    }

    /**
     * Добавить элемент в дерево.
     *
     * @param value значение.
     * @return true в случае успешного добавления иначе false.
     */
    public boolean add(E value) {
        boolean result = false;
        if (root == null) {
            root = new Node<>(value);
            result = true;
        } else {
            result = addElementToTree(root, value);
        }
        return result;
    }

    /**
     * Рекурсивный метод для нахождения места для вставки и вставки элемента.
     * @param node - текущий узел
     * @param value - значение текущего узла
     * @return true в случае успешного добавления, иначе - false
     */
    public boolean addElementToTree(Node<E> node, E value) {
        boolean result = false;
        if (value.compareTo(node.value) > 0 && node.right == null) {
            node.right = new Node<E>(value);
            result = true;
        } else if (value.compareTo(node.value) > 0 && node.right != null) {
            result = addElementToTree(node.right, value);
        } else if (value.compareTo(node.value) <= 0 && node.left == null) {
            node.left = new Node<E>(value);
            result = true;
        } else if (value.compareTo(node.value) <= 0 && node.left != null) {
            result = addElementToTree(node.left, value);
        }
        return result;
    }

    /**
     * Метод для прохода по дереву и созданию списка значений для итератора.
     * @param node узел дерева.
     * @return если массив получится пустым при заполнении вернет false иначе true
     */
    public boolean createArrayValues(Node<E> node) {
        boolean notEmptyArray = iterList.size() > 0;
        iterList.add(node.value);
        if (node.left != null) {
            notEmptyArray = createArrayValues(node.left);
        }
        if (node.right != null) {
            notEmptyArray = createArrayValues(node.right);
        }
        return notEmptyArray;
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
                boolean res = false;
                if (iterListIsEnd) {
                    res = createArrayValues(root);
                    iterListIsEnd = !res;
                } else if (iterList.size() > 0) {
                    res = true;
                } else {
                    iterListIsEnd = true;
                }
                return res;
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
