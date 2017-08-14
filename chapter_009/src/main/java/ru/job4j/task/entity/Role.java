package ru.job4j.task.entity;

/**
 * Класс, описывающий роль.
 * @author agavrikov
 * @since 14.08.2017
 * @version 1
 */
public class Role {

    /**
     * Идентификатор.
     */
    private int id;

    /**
     * Наименование роли.
     */
    private String name;

    /**
     * конструктор для инициализации полей.
     * @param id идентификатор
     * @param name имя роли
     */
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Геттер имени.
     * @return имя роли
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер имени роли.
     * @param name имя роли
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Геттер идентификатора роли.
     * @return идентификатор роли
     */
    public int getId() {
        return id;
    }

}
