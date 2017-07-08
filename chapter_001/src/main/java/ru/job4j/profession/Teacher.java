package ru.job4j.profession;

/**
 * Class Teacher.
 * @author agavrikov
 * @since 07.07.2017
 * @version 1
 */
public class Teacher extends Profession {

    /**
     * Поле для хранения предмета.
     */
    private String subject;

    /**
     * Поле отражающее репетиторство.
     */
    private boolean individualSessions;

    /**
     * Поле для хранения количества лет преподавания.
     */
    private int yearsTeaching;

    /**
     * Конструктор, инициализирующий поля.
     * @param subject - предмет
     * @param individualSessions - репититорство
     * @param yearsTeaching - стаж
     */
    public Teacher(int yearsTeaching, boolean individualSessions, String subject) {
        super("Teacher");
        this.subject = subject;
        this.individualSessions = individualSessions;
        this.yearsTeaching = yearsTeaching;
    }

    /**
     * Метод реализующий работу над построением графика.
     * @param human - человек
     * @return строка о результате лечения
     */
    public String teach(Human human) {
        //реализация
        return "Schoolboy " + human.getName() + " trained.";
    }
}
