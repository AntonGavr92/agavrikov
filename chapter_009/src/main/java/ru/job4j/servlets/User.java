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
     * Идентификатор пользователя.
     */
    private int id;

    /**
     * Email пользователя.
     */
    private String email;

    /**
     * Login пользователя.
     */
    private String login;

    /**
     * Password пользователя.
     */
    private String password;

    /**
     * Дата создания пользователя.
     */
    private long createDate;

    /**
     * Идентификатор роли пользователя.
     */
    private int idRole;

    /**
     * Город пользователя.
     */
    private City city;

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
     * Конструктор для инициализации полей пользователя.
     * @param name имя
     * @param email почта
     * @param login логин
     * @param id идентификатор пользователя
     * @param password пароль
     * @param idRole идентификатор роли пользователя
     */
    public User(int id, String name, String email, String login, String password, int idRole) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.id = id;
        this.password = password;
        this.idRole = idRole;
    }

    /**
     * Конструктор для инициализации полей пользователя при создании нового пользователя.
     * @param name имя
     * @param email почта
     * @param login логин
     * @param password пароль пользователя\
     * @param idRole идентификатор роли пользователя
     */
    public User(String name, String email, String login, String password, int idRole) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.idRole = idRole;
    }

    /**
     * Конструктор для инициализации полей пользователя для получения пользователя без пароля.
     * @param name имя
     * @param email почта
     * @param login логин
     */
    public User(String name, String email, String login) {
        this.name = name;
        this.email = email;
        this.login = login;
    }

    /**
     * Геттер пароля.
     * @return пароля пользователя.
     */
    public String getPassword() {
        return this.password;
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
     * Геттер идентификатора пользователя.
     * @return идентификатор пользователя.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Геттер идентификатора роли пользователя.
     * @return идентификатор роли пользователя.
     */
    public int getIdRole() {
        return this.idRole;
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

    /**
     * Геттер города пользователя.
     * @return город пользователя.
     */
    public City getCity() {
        return city;
    }

    /**
     * Сеттер города пользователя.
     * @param city город пользователя
     */
    public void setCity(City city) {
        this.city = city;
    }

}
