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
     * @param name - наименование графика
     * @param description - описание графика
     * @return - график
     */
    public Chart createChart(String name, String description) {
        return new Chart(this, name, description);
    }

    /**
     * Метод, осуществляющий реализацию чертежа.
     * @param chart - чертеж
     * @return строка об успешной реализации
     */
    public String realizeChart(Chart chart) {
        return "Chart " + chart.getName() + " is realized";
    }
}
