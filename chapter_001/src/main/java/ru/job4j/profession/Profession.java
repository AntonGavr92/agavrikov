package ru.job4j.profession;

/**
 * Class Profession.
 * @author agavrikov
 * @since 07.07.2017
 * @version 1
 */
public class Profession {
    /**
     * Поле для хранения наименования профессии.
     */
    private String name;

    /**
     * Конструктор, инициализирующий поле name.
     *
     * @param name - строка - наименование профессии
     */
    public Profession(String name) {
        this.name = name;
    }

    /**
     * Геттер, возвращающий наименование профессии.
     *
     * @return наименование профессии
     */
    public String getName() {
        return this.name;
    }
}
