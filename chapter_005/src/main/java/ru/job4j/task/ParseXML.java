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
     * Поле для хранения результирующей карты.
     */
    private HashMap<Integer, Order> orderMap = new HashMap<Integer, Order>();

    //private static final String FILE_NAME = "C:\\projects\\agavrikov\\chapter_005\\src\\main\\java\\ru\\job4j\\task\\orders.xml";

    /**
     * Вспомогательный метод для обработки элемента xml.
     * @param xmlr - XMLStreamReader
     */
    private void proccessElementXML(XMLStreamReader xmlr) {
        if (xmlr.getName().toString().equals("AddOrder")) {
            Order order = new Order();
            order.price = Double.parseDouble(xmlr.getAttributeValue(2));
            order.operation = xmlr.getAttributeValue(1);
            order.book = xmlr.getAttributeValue(0);
            order.orderId = Integer.parseInt(xmlr.getAttributeValue(4));
            order.volume = Integer.parseInt(xmlr.getAttributeValue(3));
            this.orderMap.put(Integer.parseInt(xmlr.getAttributeValue(4)), order);
        } else if (xmlr.getName().toString().equals("DeleteOrder")) {
            this.orderMap.remove(Integer.parseInt(xmlr.getAttributeValue(1)));
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
