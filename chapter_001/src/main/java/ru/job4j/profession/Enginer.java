package ru.job4j.profession;

/**
 * Class Enginer.
 * @author agavrikov
 * @since 07.07.2017
 * @version 1
 */
public class Enginer extends Profession {

    /**
     * Поле для хранения возраста.
    */
    private int age;

    /**
     * Поле отражающее опыт работы в годах.
    */
    private int expirience;

    /**
     * Поле для хранения наименования компании.
    */
    private String company;

    /**
     * Конструктор, инициализирующий поля.
     * @param age - возраст
     * @param expirience - стаж
     * @param company - наименование компании
     */
    public Enginer(int age, int expirience, String company) {
        super("Enginer");
        this.age = age;
        this.expirience = expirience;
        this.company = company;
    }

    /**
     * Метод реализующий работу над построением графика.
     *
     * @return строка о результате лечения
     */
    public String createChart() {
        //реализация
        return "Chart ready";
    }
}
