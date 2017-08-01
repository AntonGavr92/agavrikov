package ru.job4j.task;

/**
 * Класс описывающий лягушку.
 * @author agavrikov
 * @since 01.08.2017
 * @version 1
 */
public class Frog {

    /**
     * Поле для хранения строки в которой находится frog.
     */
    private final int rowPos;

    /**
     * Поле для хранения колонки в которой находится frog.
     */
    private final int colPos;

    /**
     * Поле для количества ходов лягушки.
     */
    private final int moves;

    /**
     * Конструктор для инициализации переменных.
     * @param row строка
     * @param col колонка
     * @param moves количество ходов
     */
    public Frog(int row, int col, int moves) {
        this.rowPos = row;
        this.colPos = col;
        this.moves = moves;
    }

    /**
     * Метод для изменения позиции лягушки(по факту создания новой лягушки с измененным состоянием).
     * @param row строка
     * @param col колонка
     * @return новый объект Frog
     */
    public Frog setNewPosition(int row, int col) {
        int moves = this.moves + 1;
        return new Frog(row, col, moves);
    }

    /**
     * Геттер количества ходов лягушки.
     * @return количество ходов лягушки.
     */
    public int getMoves() {
        return this.moves;
    }

    /**
     * Геттер текущей строки.
     * @return текущая строка лягушки.
     */
    public int getRowPos() {
        return this.rowPos;
    }

    /**
     * Геттер текущей колонки лягушки.
     * @return текущая колонка лягушки
     */
    public int getColPos() {
        return this.colPos;
    }

}
