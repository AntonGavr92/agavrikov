package ru.job4j.task.entity;

/**
 * Класс, описывающий фильтр в списке пользователей.
 * @author agavrikov
 * @since 14.08.2017
 * @version 1
 */
public class Filter {

    /**
     * Идентификатор фильтра.
     */
    private int id;

    /**
     * Наименование.
     */
    private String name;

    /**
     * Конструктор.
     * @param id идентификатор
     * @param name наименование
     */
    public Filter(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Геттер идентификатора.
     * @return идентификатор
     */
    public int getId() {
        return this.id;
    }

    /**
     * Геттер наименования.
     * @return наименование
     */
    public String getName() {
        return this.name;
    }
}
