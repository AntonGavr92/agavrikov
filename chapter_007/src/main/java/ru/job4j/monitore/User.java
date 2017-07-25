package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Класс описывающий пользователя.
 * @author agavrikov
 * @since 25.07.2017
 * @version 1
 */
@ThreadSafe
public class User {

    /**
     * поле для хранения количества (ден средств).
     */
    @GuardedBy("this")
    private int amount;

    /**
     * поле для хранения id.
     */
    private int id;

    /**
     * Конструктор.
     * @param id идентификатор пользователя.
     * @param amount количество.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Геттер количества.
     * @return количество.
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Сеттер количества.
     * @param amount количество.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Геттер идентификатора.
     * @return идентификатор пользователя.
     */
    public int getId() {
        return this.amount;
    }

    /**
     * Сеттер идентификатора.
     * @param id идентификатор пользователя.
     */
    public void setId(int id) {
        this.id = id;
    }


}
