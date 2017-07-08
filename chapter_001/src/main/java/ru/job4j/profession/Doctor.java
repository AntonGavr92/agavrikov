package ru.job4j.profession;

/**
 * Class Doctor.
 * @author agavrikov
 * @since 07.07.2017
 * @version 1
 */
public class Doctor extends Profession {

    /**
     * Поле для хранения спецификации.
    */
    private String specification;

    /**
     * Поле отражающее в наличии ли медицинский диплом.
    */
    private boolean medicalDiploma;

    /**
     * Поле для хранения идентификатора клиники.
    */
    private int idClinic;

    /**
     * Конструктор, инициализирующий поля.
     * @param idClinic - идентификатор клиники
     * @param medicalDiploma - Наличие диплома
     * @param specification - спецификация
     */
    public Doctor(int idClinic, boolean medicalDiploma, String specification) {
        super("Doctor");
        this.idClinic = idClinic;
        this.medicalDiploma = medicalDiploma;
        this.specification = specification;
    }

    /**
     * Метод реализующий лечение пациента (позитивный).
     *
     * @param human - человек
     * @return строка о результате лечения
     */
    public String heal(Human human) {
        //реализация
        return "Patient " + human.getName() + " healthy.";
    }
}
