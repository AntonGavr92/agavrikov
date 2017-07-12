package ru.job4j.chess;

/**
 * Class описывающий исключительные ситуации при отсутствии фигуры на доске.
 * @author agavrikov
 * @since 11.07.2017
 * @version 1
 */
public class FigureNotFoundException extends Exception {

    /**
     * Метод, передающий msg в конструктор родителя.
     * @param msg - строка с указанием ошибки
     */
    public FigureNotFoundException(String msg) {
        super(msg);
    }

}
