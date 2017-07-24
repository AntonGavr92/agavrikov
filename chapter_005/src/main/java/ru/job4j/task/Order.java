package ru.job4j.task;

/**
 * Класс описывающий книжный заказ.
 * @author agavrikov
 * @since 24.07.2017
 * @version 1
 */
public class Order {
    /**
     * Поле для хранения операции.
     */
    String operation;
    /**
     * Поле для хранения цены.
     */
    double price;
    /**
     * Поле для хранения объема.
     */
    int volume;
    /**
     * Поле для хранения идентификатора заказа.
     */
    int orderId;
    /**
     * Поле для хранения наименования книги.
     */
    String book;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return orderId;
    }
}
