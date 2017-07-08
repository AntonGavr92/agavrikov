package ru.job4j.profession;

/**
 * Class Класс отображающий человека.
 * @author agavrikov
 * @since 08.07.2017
 * @version 1
 */
public class Human {
    /**
     * Поле для хранения имени человека.
     */
    private String name;

    /**
     * Поле для хранения возраста человека.
     */
    private int age;

    /**
     * Поле для хранения пола человека.
     */
    private String sex;

    /**
     * Конструктор.
     * @param name - имя человека
     * @param age - возраст человека
     * @param sex - пол человека
     */
    public Human(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    /**
     * Геттер name.
     * @return имя человека
     */
    public String getName() {
        return this.name;
    }

}
