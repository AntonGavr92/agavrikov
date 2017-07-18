package ru.job4j.list;

/**
 * Класс для поиска замыкания в связном списке.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 */
public class CheckLoop {

    /**
     * Метод для поиска цикличности в ссвязном списке.
     * @param first первый элемент связного списка.
     * @return true, если есть замыкание.
     */
    boolean hasCycle(MyNode first) {
        boolean result = false;
        MyNode slow = first;
        MyNode fast = first;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }
        }
        return result;
    }
}
