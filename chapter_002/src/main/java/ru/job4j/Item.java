package ru.job4j;

import java.util.ArrayList;

/**
 * Class реализующий заявку трекера.
 * @author agavrikov
 * @since 08.07.2017
 * @version 1
 */
public class Item {
    /**
     * Уникальный идентификатор заявки.
     */
    private String id;

    /**
     * Наименование заявки.
     */
    private String name;

    /**
     * Описание заявки.
     */
    private String desc;

    /**
     * Дата созадния заявки в миллисекундах.
     */
    private long created;

    /**
     * Комментарии к заявке.
     */
    private ArrayList<String> comments;

    /**
     * Конструктор.
     * @param id - Уникальный идентификатор заявки
     * @param name - Название заявки
     * @param desc - Описание заявки
     * @param created - Дата и время создания заявки в милисекундах
     * @param comments - Комментарии к заявке
     */
    public Item(String id, String name, String desc, long created, ArrayList<String> comments) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.comments = comments;
    }

    /**
     * Конструктор.
     * @param id - Уникальный идентификатор заявки
     * @param name - Название заявки
     * @param desc - Описание заявки
     * @param created - Дата и время создания заявки в милисекундах
     */
    public Item(String id, String name, String desc, long created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    /**
     * Геттер идентификатора заявки.
     * @return - идентификатор заявки
     */
    public String getId() {
        return this.id;
    }

    /**
     * Геттер наимаенования заявки.
     * @return - наимаенование заявки
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер описания заявки.
     * @return - описание заявки
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Геттер даты создания заявки заявки.
     * @return - дата заявки
     */
    public long getCreated() {
        return this.created;
    }

    /**
     * Геттер комментариев заявки.
     * @return - наимаенование заявки
     */
    public ArrayList<String> getComments() {
        return this.comments;
    }


}
