package ru.job4j.chess;

/**
 * Класс для описания поля на шахматной доске.
 * @author agavrikov
 * @since 11.07.2017
 * @version 1
 */
public class Cell {
    /**
     * Поле для хранения фигуры, которая стоит на данном поле.
     */
    private Figure figure;

    /**
     * Поле для хранения доски, которой принадлежит ячейка.
     */
    private final Board board;

    /**
     * Поле для хранения индекс строки ячейки.
     */
    private final int row;

    /**
     * Поле для хранения индекса ячейки.
     */
    private final int col;

    /**
     * Конструктор.
     * @param board - доска
     * @param row строка ячейки.
     * @param col колонка ячейки.
     */
    public Cell(Board board, int row, int col) {
        this.board = board;
        this.row = row;
        this.col = col;
    }

    /**
     * Геттер фигуры в данной ячейке.
     * @return фигура
     */
    public Figure getFigure() {
        return this.figure;
    }

    /**
     * Геттер, возвращающий нам объект - доску, на которой расположена ячейка.
     * @return объект доски
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Геттер, возвращающий нам строку в которой находится ячейка.
     * @return объект доски
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Геттер, возвращающий нам колонку ячейки.
     * @return объект доски
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Сеттер фигуры в заданную ячейку.
     * @param figure - фигура, которуй надо установить на ячейку.
     *
     */
    public void setFigure(Figure figure) {
        this.figure = figure;
    }

}
