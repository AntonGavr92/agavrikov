package ru.job4j.task;

/**
 * Класс описывающий положение.
 * @author agavrikov
 * @since 01.08.2017
 * @version 1
 */
public class Move {

    /**
     * Поле для хранения строки.
     */
    private int row;

    /**
     * Поле для хранения колонки.
     */
    private int col;

    /**
     * Конструктор для инициализации.
     * @param row строка
     * @param col колонка
     */
    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Геттер колонки.
     * @return колонка
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Геттер строки.
     * @return строка
     */
    public int getRow() {
        return this.row;
    }

}
