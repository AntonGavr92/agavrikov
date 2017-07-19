package ru.job4j.map;

/**
 * Класс определющий пользователя.
 * @author agavrikov
 * @since 19.07.2017
 * @version 1
 */
public class User {
    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Количество детей.
     */
    private int children;

    /**
     * День рождения пользователя.
     */
    private Calendar birthday;

    /**
     * Конструктор.
     * @param name имя пользователя.
     * @param children количество детей у пользователя.
     * @param birthday день рождения пользователя.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
