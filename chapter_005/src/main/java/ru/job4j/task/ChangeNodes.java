package ru.job4j.task;

import ru.job4j.tree.BinaryTree;
import ru.job4j.tree.BinaryTree.Node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Класс описывающий бинарное дерево и реализующий замену правого и левого узла.
 * @author agavrikov
 * @since 24.07.2017
 * @version 1
 */
public class ChangeNodes<T extends Comparable<T>> implements Iterable<T> {

    /**
     * Поле для хранения дерева.
     */
    private BinaryTree<T> tree = new BinaryTree<T>();

    /**
     * Метод для вызова метода с рекурсией, для замены правого и левого узла с обходом дерева в глубину.
     */
    public void heightChangeNodes() {
        recHeightChangeNodes(this.tree.getRoot());
    }

    /**
     * Рекурсивный метод для замены правого и левого узла с обходом дерева в глубину.
     * @param node текущий узел.
     */
    private void recHeightChangeNodes(Node<T> node) {
        if(node.left != null) {
            recHeightChangeNodes(node.left);

        }
        if(node.right != null) {
            recHeightChangeNodes(node.right);
        }
        Node<T> tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    /**
     * Метод для вызова метода, для замены правого и левого узла с обходом дерева в ширину.
     */
    public void widthChangeNodes() {
        recWidthChangeNodes(this.tree.getRoot());
    }

    /**
     * Метод для замены правого и левого узла с обходом дерева в ширину, с помощью очереди.
     * @param node корень дерева
     */
    private void recWidthChangeNodes(Node<T> node) {
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        queue.add(node);
        while (queue.size() > 0) {
            Node<T> currNode = queue.poll();
            if (currNode.left != null) {
                queue.add(currNode.left);
            }
            if (currNode.right != null) {
                queue.add(currNode.right);
            }
            Node<T> tmp = currNode.left;
            currNode.left = currNode.right;
            currNode.right = tmp;
        }
    }

    /**
     * Метод для добавление нового узла.
     * @param value значение
     */
    public void add(T value) {
        tree.add(value);
    }

    @Override
    public Iterator<T> iterator() {
        return this.tree.iterator();
    }
}
