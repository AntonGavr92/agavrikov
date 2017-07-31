package ru.job4j.jdbc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Класс описывающий взаимодействие с xml файлами.
 * @author agavrikov
 * @since 31.07.2017
 * @version 1
 */
public class XmlWriter {

    /**
     * Путь к папке, в которой необходимо развертывание файлов.
     */
    private String path;

    /**
     * Поле для хранении имени файла, сформированного из бд.
     */
    private static final String NAME_FIRST_XML = "1.xml";

    /**
     * Поле для хранении имени файла, сформированного из первого файла с исправленной структурой.
     */
    private static final String NAME_CONVERTER_XML = "2.xml";

    /**
     * Поле для хранении имени файла, описывающего новую структуру файла.
     */
    private static final String NAME_XSL_FILE = "struct.xsl";

    /**
     * Поле для хранении индекса аттрибута во втором файле.
     */
    private static final int ATTR_INDEX = 0;


    /**
     * Конструктор для инициализации.
     * @param path путь до папки
     */
    public XmlWriter(String path) {
        this.path = path;
    }

    /**
     * Метод для создание файла xml из ArrayList.
     * @param data ArrayList с данными
     */
    public void createXML(ArrayList<Integer> data) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            Document doc = factory.newDocumentBuilder().newDocument();
            Element entries = doc.createElement("entries");
            doc.appendChild(entries);
            for (Integer num : data) {
                Element entry = doc.createElement("entry");
                Element field = doc.createElement("number");
                field.setNodeValue(num.toString());
                field.setTextContent(num.toString());
                entry.appendChild(field);
                entries.appendChild(entry);
            }
            File file = new File(String.format("%s%s", path, NAME_FIRST_XML));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(file));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для получения суммы из сконвертированного файла.
     * @return сумму значений аттрибутов
     */
    public int getSumFromXml() {
        int i = 0;
        String fileName = String.format("%s%s", path, NAME_CONVERTER_XML);
        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(fileName, new FileInputStream(fileName));
            while (xmlr.hasNext()) {
                xmlr.next();
                if (xmlr.isStartElement() && xmlr.getName().toString().equals("entry")) {
                    i += Integer.parseInt(xmlr.getAttributeValue(ATTR_INDEX));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * Метод для конвертации первоначального xml в другую структуру.
     */
    public void createTransformXml() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            File file = new File(String.format("%s%s", path, NAME_CONVERTER_XML));
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer(new StreamSource(String.format("%s%s", path, NAME_XSL_FILE)));
            transformer.transform(new StreamSource(String.format("%s%s", path, NAME_FIRST_XML)), new StreamResult(file));

        }  catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
