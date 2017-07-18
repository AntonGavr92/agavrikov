package ru.job4j.task;

/**
 * Class описывающий посетителя банка.
 * @author agavrikov
 * @since 17.07.2017
 * @version 1
 */
public class Customer {

    /**
     * Поле для хранения временив хода в банк.
     */
    private long timeEnter;

    /**
     * Поле для хранения времени выхода в банк.
     */
    private long timeExit;

    /**
     * Конструктор.
     * @param timeEnter - время входа
     * @param timeExit - время выхода
     */
    public Customer(long timeEnter, long timeExit) {
        this.timeEnter = timeEnter;
        this.timeExit = timeExit;
    }

    /**
     * Геттер времени входа.
     * @return время входа
     */
    public long getTimeEnter() {
        return this.timeEnter;
    }

    /**
     * Геттер времени выхода.
     * @return время выхода
     */
    public long getTimeExit() {
        return this.timeExit;
    }
}
