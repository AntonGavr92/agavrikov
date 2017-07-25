package ru.job4j.task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


/**
 * Класс реализующий чтение файла XML и создание карты заказов книг.
 * @author agavrikov
 * @since 24.07.2017
 * @version 1
 */
public class ParseXML {

    /**
     * Индекс атрибута цены.
     */
    public static final int ATTR_INDEX_PRICE = 2;

    /**
     * Индекс атрибута операции.
     */
    public static final int ATTR_INDEX_OPERATION = 1;

    /**
     * Индекс атрибута наименования книги.
     */
    public static final int ATTR_INDEX_NAME = 0;

    /**
     * Индекс атрибута идентификатора заказа при операции добавления заказа.
     */
    public static final int ATTR_INDEX_ADD_ORDER_ID = 4;

    /**
     * Индекс атрибута объема.
     */
    public static final int ATTR_INDEX_VOLUME = 3;

    /**
     * Индекс атрибута идентификатора заказа при операции удаления заказа.
     */
    public static final int ATTR_INDEX_DEL_ORDER_ID = 1;

    /**
     * Имя тега для добавления заказа.
     */
    public static final String ADD_ORDER_TAG = "AddOrder";

    /**
     * Имя тега для удаления заказа.
     */
    public static final String DELETE_ORDER_TAG = "DeleteOrder";

    /**
     * Поле для хранения результирующей карты.
     */
    private HashMap<Integer, Order> orderMap = new HashMap<Integer, Order>();

    //private static final String FILE_NAME = "C:\\projects\\agavrikov\\chapter_005\\src\\main\\java\\ru\\job4j\\task\\orders.xml";

    /**
     * Вспомогательный метод для обработки элемента xml.
     * @param xmlr - XMLStreamReader
     */
    private void proccessElementXML(XMLStreamReader xmlr) {
        if (ADD_ORDER_TAG.equals(xmlr.getName().toString())) {
            Order order = new Order();
            order.price = Double.parseDouble(xmlr.getAttributeValue(ATTR_INDEX_PRICE));
            order.operation = xmlr.getAttributeValue(ATTR_INDEX_OPERATION);
            order.book = xmlr.getAttributeValue(ATTR_INDEX_NAME);
            order.orderId = Integer.parseInt(xmlr.getAttributeValue(ATTR_INDEX_ADD_ORDER_ID));
            order.volume = Integer.parseInt(xmlr.getAttributeValue(ATTR_INDEX_VOLUME));
            this.orderMap.put(Integer.parseInt(xmlr.getAttributeValue(ATTR_INDEX_ADD_ORDER_ID)), order);
        } else if (DELETE_ORDER_TAG.equals(xmlr.getName().toString())) {
            this.orderMap.remove(Integer.parseInt(xmlr.getAttributeValue(ATTR_INDEX_DEL_ORDER_ID)));
        }
    }

    /**
     * Метод для обработки файла XML и создании карты закаозов.
     * @param filename путь к файлу.
     * @return карта заказов.
     */
    public HashMap<Integer, Order> proccessFile(String filename) {
        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(filename, new FileInputStream(filename));
            while (xmlr.hasNext()) {
                xmlr.next();
                if (xmlr.isStartElement()) {
                    this.proccessElementXML(xmlr);
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        return this.orderMap;
    }
}
