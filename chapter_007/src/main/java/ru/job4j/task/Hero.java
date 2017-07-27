package ru.job4j.task;

/**
 * Класс описывающий героя игры bomberman.
 * @author agavrikov
 * @since 27.07.2017
 * @version 1
 */
public class Hero {

    /**
     * Текущая позиция героя в строке.
     */
    final int row;

    /**
     * Текущая позиция героя в колонке.
     */
    final int col;

    /**
     * Инициализация полей.
     * @param row строка
     * @param col колонка
     */
    public Hero(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Изменение позиции героя, по факту создание нового экземпляра с новыми координатами.
     * @param row строка
     * @param col колонка
     * @return новый экземпляр класса.
     */
    public Hero changeField(int row, int col) {
        return new Hero(row, col);
    }
}
