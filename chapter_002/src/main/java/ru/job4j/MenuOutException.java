package ru.job4j;

/**
 * Class описывающий исключительные ситуации при пользовательском вводе.
 * @author agavrikov
 * @since 11.07.2017
 * @version 1
 */
public class MenuOutException extends RuntimeException {

    /**
     * Метод, передающий msg в конструктор родителя.
     * @param msg - строка с указанием ошибки
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
