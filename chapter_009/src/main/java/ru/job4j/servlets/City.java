package ru.job4j.servlets;

/**
 * Class описывающий город.
 * @author agavrikov
 * @since 08.08.2017
 * @version 1
 */
public class City {
    /**
     * Наименование города.
     */
    private String name;

    /**
     * Наименование страны, к которой принадлежит город.
     */
    private String country;

    /**
     * Уникальный идентификатор страны.
     */
    private int id;

    /**
     * Конструктор для инициализации.
     * @param name наименование города
     * @param country наименование страны
     * @param id идентификатор
     */
    public City(String name, String country, int id) {
        this.name = name;
        this.country = country;
        this.id = id;
    }

    /**
     * Конструктор для инициализации.
     * @param name наименование города
     * @param country наименование страны
     */
    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    /**
     * Геттер имени города.
     * @return имя города
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер имени города.
     * @param name имя города.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Геттер имени страны.
     * @return имя страны
     */
    public String getCountry() {
        return country;
    }

    /**
     * Сеттер имени страны.
     * @param country имя страны.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Геттер идентификатора города.
     * @return идентификатор города
     */
    public int getId() {
        return id;
    }
}
