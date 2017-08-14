package ru.job4j.task.entity;

/**
 * Класс, описывающий музыкальный тип.
 * @author agavrikov
 * @since 14.08.2017
 * @version 1
 */
public class MusicType {

    /**
     * Идентификатор музыкального типа.
     */
    private int id;

    /**
     * Музыкальный тип.
     */
    private String type;

    /**
     * Конструктор для инициализации.
     * @param id идентификатор
     * @param type  музыкальный тип
     */
    public MusicType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Геттер идентификатора типа.
     * @return идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Геттер музыкального типа.
     * @return музыкальный тип.
     */
    public String getType() {
        return type;
    }

    /**
     * Сеттер музыкального типа.
     * @param type музыкальный тип.
     */
    public void setType(String type) {
        this.type = type;
    }
}
