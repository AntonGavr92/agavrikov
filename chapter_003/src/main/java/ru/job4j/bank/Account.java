package ru.job4j.bank;

/**
 * Класс для описания банковского счета.
 * @author agavrikov
 * @since 14.07.2017
 * @version 1
 */
public class Account {

    /**
     * Поле для хранения количества средств на счете.
     */
    private double value;

    /**
     * Поле для хранения реквизитов счета.
     */
    private String requisites;

    /**
     * Конструктор для инициализации полей банковского счета.
     * @param value - количество денежных средств.
     * @param requisites - реквизиты банка.
     */
    public Account(int value, String requisites) {
        this.value = value;
        this.requisites = requisites;

    }

    /**
     * Геттер количества денежных средств.
     * @return кол-во денежных средств
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Геттер реквизитов
     * @return кол-во денежных средств.
     */
    public String getRequisites() {
        return this.requisites;
    }

    /**
     * Сеттер количества денежных средств.
     * @param value кол-во денежных средств
     */
    public void setValue(double value) {
        this.value = value;
    }
}
