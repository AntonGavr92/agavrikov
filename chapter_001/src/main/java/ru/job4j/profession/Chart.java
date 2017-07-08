package ru.job4j.profession;

/**
 * Class Класс реализующий чертеж.
 * @author agavrikov
 * @since 08.07.2017
 * @version 1
 */
public class Chart {
    /**
     * Ответственный за чертеж.
     */
    private Enginer responsible;

    /**
     * Наименование чертежа.
     */
    private String name;

    /**
     * Описание чертежа.
     */
    private String description;

    /**
     * Конструктор.
     * @param responsible - ответственный
     * @param name - имя
     * @param description - описание
     */
    public Chart(Enginer responsible, String name, String description) {
        this.description = description;
        this.name = name;
        this.responsible = responsible;
    }

    /**
     * Геттер name.
     * @return - наименование чертежа
     */
    public String getName() {
        return this.name;
    }
}
