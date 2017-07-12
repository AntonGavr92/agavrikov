package ru.job4j.chess;

/**
 * Class описывающий исключительные ситуации при невозможности движения фигуры.
 * @author agavrikov
 * @since 11.07.2017
 * @version 1
 */
public class ImpossibleMoveException extends Exception {

    /**
     * Метод, передающий msg в конструктор родителя.
     * @param msg - строка с указанием ошибки
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }

}
