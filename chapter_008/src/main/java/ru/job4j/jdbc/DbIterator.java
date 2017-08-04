package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

/**
 * Класс описывающий взаимодействие с таблицей в базе данных.
 * @author agavrikov
 * @since 31.07.2017
 * @version 1
 */
public class DbIterator {

    /**
     * Поле для хранения объекта логгирования.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DbIterator.class);

    /**
     * Поле для хранения объекта, с данными о подключении к бд.
     */
   // private DataConnection dataConnection;

    /**
     * Конструктор по-умолчанию, для инициализации подключения к бд.
     */
    public DbIterator() {
        //this.dataConnection = new DataConnection("localhost", "5432", "postgres", "12345678", "task", "postgres");
    }

    /**
     * Метод для проверки наличия таблицы в базе данных.
     * @param tableName наименование таблицы
     * @return true - если таблица существует, иначе false
     */
    public boolean tableIsNotExist(String tableName) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            LOG.error("Ошибка подключения драйвера.", e);
        }
        boolean result = true;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
             PreparedStatement ps = conn.prepareStatement("select * from sqlite_master where type = 'table' AND name = ?;")){
            ps.setString(1, tableName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = false;
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд.", e);
        }
        return result;
    }

    /**
     * Методд для создания таблицы в бд.
     * @param tableName наименование таблицы
     */
    public void createTable(String tableName) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            LOG.error("Ошибка подключения драйвера.", e);
        }
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
             PreparedStatement ps = conn.prepareStatement(String.format("CREATE TABLE %s (number INTEGER)", tableName))) {
            //Не сработало, полагаю из за ковычек..
            ps.execute();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд.", e);
        }
    }

    /**
     * Метод для проверки таблицы на наличие записей.
     * @param tableName имя таблицы
     * @return true - если таблица пустая, иначе false.
     */
    public boolean tableEmpty(String tableName) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            LOG.error("Ошибка подключения драйвера.", e);
        }
        boolean result = true;
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
             PreparedStatement ps = conn.prepareStatement(String.format("SELECT * FROM %s", tableName))) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = false;
                break;
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд.", e);
        }
        return result;
    }

    /**
     * Метод для очистки таблицы.
     * @param tableName имя таблицы, которую необходимо очистить.
     */
    public void clearTable(String tableName) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            LOG.error("Ошибка подключения драйвера.", e);
        }
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
             PreparedStatement ps = conn.prepareStatement(String.format("DELETE FROM %s", tableName))) {
            ps.execute();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд.", e);
        }
    }

    /**
     * Метод для создание записей в таблице.
     * @param count количество записей в таблице
     * @param tableName имя таблицы
     */
    public void createEntryInDb(int count, String tableName) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            LOG.error("Ошибка подключения драйвера.", e);
        }
        if (tableIsNotExist(tableName)) {
            createTable(tableName);
        } else if (!tableEmpty(tableName)) {
            clearTable(tableName);
        }
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
             PreparedStatement ps = conn.prepareStatement(String.format("INSERT INTO %s (number) VALUES (?)", tableName));) {
            for (int i = 1; i <= count; i++) {
                ps.setInt(1, i);
                ps.execute();
            }
            ps.close();
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд.", e);
        }
    }

    /**
     * Метод для получения данных из таблицы и конвертацию в arraylist.
     * @param tableName имя таблицы
     * @return список всех записе й таблицы
     */
    public ArrayList<Integer> getDataFromTable(String tableName) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            LOG.error("Ошибка подключения драйвера.", e);
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
             Statement ps = conn.createStatement()) {
            ResultSet rs = ps.executeQuery(String.format("SELECT * FROM %s", tableName));
            while (rs.next()) {
                result.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            LOG.error("Ошибка при работе с бд.", e);
        }
        return result;
    }

    /**
     * Точка входа в программу.
     * @param args параметры
     */
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        DbIterator it = new DbIterator();
        it.createEntryInDb(1000000, "test");
        XmlWriter xml = new XmlWriter("chapter_008\\src\\tmp\\");
        xml.createXML(it.getDataFromTable("test"));
        xml.createTransformXml();
        int res = xml.getSumFromXml();
        System.out.println(System.currentTimeMillis() - time);
    }

}
