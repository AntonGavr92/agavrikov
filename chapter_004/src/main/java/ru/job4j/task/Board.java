package ru.job4j.task;

/**
 * Board interface.
 * @author agavrikov
 * @since 28.08.2017
 * @version 1
 */
public interface Board {

    /**
     * Method for get fields.
     * @return fields
     */
    SimpleField[][] fields();

    /**
     * Method for move on board.
     * @param mark mark
     * @param i row
     * @param j col
     */
    void move(SimpleMark mark, int i, int j);

    /**
     * Method for check available moves.
     * @return true if available moves founded, else - false.
     */
    boolean hasMoves();

}
