package ru.job4j;

/**
 * Класс определяющий пользователя.
 * @author agavrikov
 * @since 13.07.2017
 * @version 1
 */
public class User {

    /**
     * Поле для хранения идентификатора пользователя.
     */
    private int id;

    /**
     * Поле для хранения города пользователя.
     */
    private String city;

    /**
     * Поле для хранения идентификатора пользователя.
     */
    private String name;

    /**
     * Конструктор, для инициализации объекта.
     * @param id - id пользователя
     * @param name - имя пользователя
     * @param city - город пользователя
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Геттер id.
     * @return идентификатор пользователя
     */
    public int getId() {
        return this.id;
    }

    /**
     * Геттер имени пользователя.
     * @return идентификатор пользователя
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер города пользователя.
     * @return город пользователя
     */
    public String getCity() {
        return this.city;
    }

}
