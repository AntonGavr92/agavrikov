package ru.job4j.task;

import java.util.ArrayList;

/**
 * Created by gavrikov.a on 24/08/2017.
 */
public class SmallBoard implements Board {

    private SimpleField[][] fields = new SimpleField[3][3];

    private int countMoves;

    public SmallBoard() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = new SimpleField();
                countMoves++;
            }
        }
    }

    @Override
    public void move(Mark mark, int i, int j) {
        if(this.fields[i][j].isEmpty()) {
            this.fields[i][j].setMark(mark);
        }
    }

    @Override
    public boolean hasMoves() {
        boolean result = false;
        if (countMoves > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public Field[][] getFieldsBoard() {
        return this.fields;
    }
}
