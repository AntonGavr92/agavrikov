package ru.job4j.servlets;

/**
 * Class описывающий пользователя.
 * @author agavrikov
 * @since 08.07.2017
 * @version 1
 */
public class User {

    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Email пользователя.
     */
    private String email;

    /**
     * Login пользователя.
     */
    private String login;

    /**
     * Дата создания пользователя.
     */
    private long createDate;

    /**
     * Конструктор для инициализации полей пользователя.
     * @param name имя
     * @param email почта
     * @param login логин
     * @param createDate дата создания
     */
    public User(String name, String email, String login, long createDate) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.createDate = createDate;
    }

    /**
     * Геттер имени.
     * @return имя пользователя.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер почты.
     * @return почта пользователя.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Геттер логина.
     * @return логин пользователя.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Геттер даты создания пользователя.
     * @return дата создания пользователя.
     */
    public long getCreateDate() {
        return this.createDate;
    }

    /**
     * Сеттер имени пользователя.
     * @param name имя пользователя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Сеттер почты пользователя.
     * @param email почта пользователя
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Сеттер логина пользователя.
     * @param login логин пользователя
     */
    public void setLogin(String login) {
        this.login = login;
    }

}
