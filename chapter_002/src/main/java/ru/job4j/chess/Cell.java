package ru.job4j.chess;

/**
 * Класс для описания поля на шахматной доске.
 * @author agavrikov
 * @since 11.07.2017
 * @version 1
 */
public class Cell {

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
     * @param row строка ячейки.
     * @param col колонка ячейки.
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
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
}
