package ru.job4j;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;


/**
 * Class реализующий трекер заявок.
 * @author agavrikov
 * @since 08.07.2017
 * @version 1
 */
public class Tracker {

    /**
     * log.
     */
    private static final Logger log = Logger.getLogger(Tracker.class);

    /**
     * Заявки трекера.
     */
    private ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Поле для хранения данных о подключении к бд.
     */
    DataConnection dataConnection = new DataConnection("localhost", "5432", "postgres", "12345678", "task", "postgresql");

    /**
     * Метод для добавления заявки в трекер.
     * @param item - заявка
     * @return - добавленная заявка
     */
    public Item add(Item item) {
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("INSERT INTO items (id, name, description, created) VALUES (?, ?, ?, ?)");
             PreparedStatement ps2 = conn.prepareStatement("INSERT INTO items (id, name, description, created) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getDesc());
            ps.setLong(4, item.getCreated());
            ps.execute();
            ps.close();
            if (item.getComments() != null) {
                ps2.setString(1, item.getId());
                ps2.setString(2, item.getComments().get(item.getComments().size()));
                ps2.execute();
            }
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return item;
    }

    /**
     * Метод для обновления заявки.
     * @param item заявка, которую нужно обновить
     */
    public void update(Item item) {
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("UPDATE items SET name = ?, description = ?, created = ? WHERE id = ?");
             PreparedStatement ps2 = conn.prepareStatement("DELETE FROM comments WHERE comments.id_item = ?");
             PreparedStatement ps3 = conn.prepareStatement("INSERT INTO comments (id_item, text) VALUES (?, ?)")) {

            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setLong(3, item.getCreated());
            ps.setString(4, item.getId());
            ps.execute();

            //Тут можно соптимизировать, за счет контроля id комментариев. А пока полоностью чистим и заполняем коментами.
            ps2.setString(1, item.getId());
            ps2.execute();

            for(String comment : item.getComments()) {
                ps3.setString(1, item.getId());
                ps3.setString(2, comment);
                ps.execute();
            }
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
    }

    /**
     * Метод для удаления заявки.
     * @param item - заявка, которую нужно удалить
     */
    public void delete(Item item) {
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("DELETE FROM comments WHERE comments.id_item = ?");
             PreparedStatement ps2 = conn.prepareStatement("DELETE FROM comments WHERE comments.id_item = ?")) {

            ps.setString(1, item.getId());
            ps.execute();

            ps2.setString(1, item.getId());
            ps.execute();
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
    }


    /**
     * Метод для поиска и отображения всех текущих заявок.
     * @return массив всех заявок
     */
    public ArrayList<Item> findAll() {
        ArrayList<Item> result = new ArrayList<Item>();
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
             Statement ps = conn.createStatement()) {
            ResultSet rs = ps.executeQuery("SELECT * FROM items");
            while (rs.next()) {
                result.add(convertRowDbToItem(rs));
            }
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return result;
    }

    /**
     * Метод для поиска заявок по имени.
     * @param key - имя для поиска
     * @return массив заявок с именем в переменной key
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> foundItems = new ArrayList<Item>();
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM items WHERE items.name = ?")) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                foundItems.add(convertRowDbToItem(rs));
            }
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return foundItems;
    }

    /**
     * Метод для поиска заявок по уникальному идентификатору.
     * @param id - уникальный идентификатор
     * @return - заявка или null, если заявки стаким идентификатором нет.
     */
    public Item findById(String id) {
        Item foundElement = null;
        try (Connection conn = DriverManager.getConnection(dataConnection.urlConnection(), dataConnection.getUser(), dataConnection.getPassword());
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM items WHERE items.id = ?")) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                foundElement = convertRowDbToItem(rs);
            }
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return foundElement;
    }


    /**
     * Метод для конвертации данных из базы данных в объект Item.
     * @param rs отбъек строки таблицы бд
     * @return собранный объект Item
     */
    private Item convertRowDbToItem(ResultSet rs) {
        Item result = null;
        try {
            result = new Item(rs.getString(1), rs.getString(2), rs.getString(3), rs.getLong(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Геттер данных соединения с бд.
     * @return объект  данных соединения с бд
     */
    public DataConnection getDataConnection() {
        return this.dataConnection;
    }

}
