package ru.job4j.nonblocking;

/**
 * Класс описывающий струткуру со значением и версией.
 * @author agavrikov
 * @since 26.07.2017
 * @version 1
 * @param <E> - объект
 */

public class ConcurrentVersion<E> {
    /**
     * Значение.
     */
    E value;
    /**
     * Версия.
     */
    volatile int version = 0;
}
