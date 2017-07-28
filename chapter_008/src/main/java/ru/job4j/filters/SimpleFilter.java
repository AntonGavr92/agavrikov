package ru.job4j.filters;

/**
 * Класс описывающий фильтр с alias.
 * @author agavrikov
 * @since 28.07.2017
 * @version 1
 */
public class SimpleFilter {

    /**
     * Имя фильтра.
     */
    private String name;

    /**
     * Запрос с фильтром.
     */
    private String query;

    /**
     * Конструктор.
     * @param name имя фильтра
     * @param query запрос с фильтром
     */
    public SimpleFilter(String name, String query) {
        this.name = name;
        this.query = query;
    }

    /**
     * Геттер.
     * @return поле name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер.
     * @return поле query
     */
    public String getQuery() {
        return this.query;
    }

}
