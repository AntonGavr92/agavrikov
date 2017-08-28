package ru.job4j.task;


/**
 * SmallBoard class.
 * @author agavrikov
 * @since 28.08.2017
 * @version 1
 */
public class SmallBoard implements Board {

    /**
     * Fields of board.
     */
    private SimpleField[][] fields = new SimpleField[3][3];

    /**
     * Count moves on board.
     */
    private int countMoves;

    /**
     * Constructor.
     */
    public SmallBoard() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = new SimpleField(new SimpleMark('_'), true);
                countMoves++;
            }
        }
    }

    /**
     * Method for move on board.
     * @param mark mark
     * @param i row
     * @param j col
     */
    @Override
    public void move(SimpleMark mark, int i, int j) {
        if(this.fields[i][j].isEmpty()) {
            this.fields[i][j] = this.fields[i][j].changeMark(mark);
        }
    }

    /**
     * Method for check available moves.
     * @return true if available moves founded, else - false.
     */
    @Override
    public boolean hasMoves() {
        boolean result = false;
        if (countMoves > 0) {
            result = true;
        }
        return result;
    }

    /**
     * Method for get fields.
     * @return fields
     */
    @Override
    public SimpleField[][] fields() {
        return this.fields;
    }
}
