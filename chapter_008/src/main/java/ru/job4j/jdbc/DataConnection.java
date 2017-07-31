package ru.job4j.jdbc;

/**
 * Класс описывающий данные для соединения с бд.
 * @author agavrikov
 * @since 31.07.2017
 * @version 1
 */
public class DataConnection {
    /**
     * Хост субд.
     */
    private String host;

    /**
     * порт субд.
     */
    private String port;

    /**
     * Пользователь субд (логин).
     */
    private String user;

    /**
     * Пароль пользователя субд (логин).
     */
    private String password;

    /**
     * База данных.
     */
    private String db;

    /**
     * url для подключения.
     */
    private String urlConnection;

    /**
     * Конструктор.
     * @param host Хост
     * @param port порт
     * @param user Пользователь
     * @param password Пароль
     * @param db База данных
     * @param typeJdbc тип субд
     */
    public DataConnection(String host, String port, String user, String password, String db, String typeJdbc) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.db = db;
        this.urlConnection = String.format("jdbc:%s://%s:%s/%s", typeJdbc, host, port, db);
    }

    /**
     * Геттер порта.
     * @return порт
     */
    public String getPort() {
        return this.port;
    }

    /**
     * Геттер хоста.
     * @return хост
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Геттер логина.
     * @return логин
     */
    public String getUser() {
        return this.user;
    }

    /**
     * Геттер пароля.
     * @return пароль
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Геттер базы данных.
     * @return база данных
     */
    public String getDb() {
        return this.db;
    }

    /**
     * Геттер url соединения.
     * @return url для соединения
     */
    public String urlConnection() {
        return this.urlConnection;
    }
}
