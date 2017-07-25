package ru.job4j.task;

import org.junit.Test;
import java.util.Date;
import java.util.TreeSet;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test OrderBook class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class OrderBookTest {

    /**
     * Метод для тестирования временных  затрат на обработку данных (не должно превышать 6 сек).
     */
    @Test
    public void testTime() {
        long timeStart = new Date().getTime();
        ParseXML parser = new ParseXML();
        OrderBook orders = new OrderBook();
        orders.addOrdersBook(parser.proccessFile("src\\tmp\\orders.xml"));
        TreeSet<Order> res = orders.getBuyBooks("book-1", false);
        long timeFinish = new Date().getTime();
        boolean result = timeFinish - timeStart > 6000;
        boolean expected = false;
        assertThat(result, is(expected));
    }

    /**
     * Метод для тестирования получения данных по продажам и тест сортировки.
     */
    @Test
    public void testBuyMethod() {
        ParseXML parser = new ParseXML();
        OrderBook orders = new OrderBook();
        orders.addOrdersBook(parser.proccessFile("src\\tmp\\orders.xml"));
        TreeSet<Order> res = orders.getBuyBooks("book-2", true);
        double price = 0;
        boolean result = true;
        for (Order order : res) {
            if (price == 0) {
                price = order.price;
            } else if (order.price > price || !order.operation.equals("BUY")) {
                result = false;
                break;
            }
        }
        boolean expected = true;
        assertThat(result, is(expected));
    }

    /**
     * Метод для тестирования получения данных по покупкам и тест сортировки.
     */
    @Test
    public void testSellMethod() {
        ParseXML parser = new ParseXML();
        OrderBook orders = new OrderBook();
        orders.addOrdersBook(parser.proccessFile("src\\tmp\\orders.xml"));
        TreeSet<Order> res = orders.getSellBooks("book-2", false);
        double price = 0;
        boolean result = true;
        for (Order order : res) {
            if (price == 0) {
                price = order.price;
            } else if (order.price < price || !order.operation.equals("SELL")) {
                result = false;
                break;
            }
        }
        boolean expected = true;
        assertThat(result, is(expected));
    }
}