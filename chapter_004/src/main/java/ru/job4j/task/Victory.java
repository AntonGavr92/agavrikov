package ru.job4j.task;

/**
 * Victory interface.
 * @author agavrikov
 * @since 28.08.2017
 * @version 1
 */
public interface Victory {

    /**
     * Method for find victory on board.
     * @param board board
     * @param lastI row
     * @param lastJ col
     * @return true? if victory founded< else false
     */
    boolean playerIsWin(Board board, int lastI, int lastJ);

}
