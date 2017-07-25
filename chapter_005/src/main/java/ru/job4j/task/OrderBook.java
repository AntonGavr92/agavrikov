package ru.job4j.task;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

/**
 * Класс реализующий хранилище заказов книг.
 * @author agavrikov
 * @since 24.07.2017
 * @version 1
 */
public class OrderBook {

    /**
     * Поле для хранения идентификатора продажи.
     */
    public static final String SELL_NAME = "SELL";

    /**
     * Поле для хранения множенства заказов книг для продажи.
     */
    private HashSet<Order> resultSell = new HashSet<Order>();

    /**
     * Поле для хранения множенства заказов книг для покупки.
     */
    private HashSet<Order> resultBuy = new HashSet<Order>();

    /**
     * Метод для добавления книжных заказов.
     * @param books карта заказов книг.
     */
    public void addOrdersBook(Map<Integer, Order> books) {
        for (Order order : books.values()) {
            if (SELL_NAME.equals(order.operation)) {
                resultSell.add(order);
            } else {
                resultBuy.add(order);
            }
        }
    }

    /**
     * Метод для получения отсортированного множества заказов продаж (Sell) с сортировкой по цене.
     * @param sortAsc сортировать по возрастанию (true) или по убыванию (false)
     * @param book книга, по которой необходимо получить данные.
     * @return отсортированное множество заказов.
     */
    public TreeSet<Order> getSellBooks(String book, boolean sortAsc) {
        TreeSet<Order> resultSet = new TreeSet<Order>(getComparator(sortAsc));
        for (Order order : resultSell) {
            if (order.book.equals(book)) {
                resultSet.add(order);
            }
        }
        return resultSet;
    }

    /**
     * Метод для получения отсортированного множества заказов покупок (Buy) с сортировкой по цене.
     * @param sortAsc сортировать по возрастанию (true) или по убыванию (false)
     * @param book книга, по которой необходимо получить данные.
     * @return отсортированное множество заказов.
     */
    public TreeSet<Order> getBuyBooks(String book, boolean sortAsc) {
        TreeSet<Order> resultSet = new TreeSet<Order>(getComparator(sortAsc));
        for (Order order : resultBuy) {
            if (order.book.equals(book)) {
                resultSet.add(order);
            }
        }
        return resultSet;
    }

    /**
     * Метод для получения компораторя для сортировки множества.
     * @param sortAsc компоратор должен сортировать по возрастанию (true) или по убыванию (false) цены.
     * @return компаратор для множества.
     */
    private Comparator<Order> getComparator(boolean sortAsc) {
        int sort = sortAsc ? 1 : -1;
        return new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                int result = sort;
                if (o1.price < o2.price) {
                    result = sort;
                } else if (o1.price > o2.price) {
                    result = -sort;
                }
                return result;
            }
        };
    }
}
