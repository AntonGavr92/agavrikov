package ru.job4j.nonblocking;

/**
 * Created by gavrikov.a on 26/07/2017.
 */
public class OptimisticException extends RuntimeException {
    /**
     * Конструктор.
     * @param error текст ошибки.
     */
    public OptimisticException(String error) {
        super(error);
    }
}
