package ru.job4j.chess;

/**
 * Class описывающий исключительные ситуации при наличии другой фигуры на пути у передвигаемой фигуры.
 * @author agavrikov
 * @since 11.07.2017
 * @version 1
 */
public class OccupiedWayException extends Exception {

    /**
     * Метод, передающий msg в конструктор родителя.
     * @param msg - строка с указанием ошибки
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }

}
