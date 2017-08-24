package ru.job4j.task;

/**
 * Created by gavrikov.a on 24/08/2017.
 */
public interface Board {

    void move(Mark mark, int i, int j);

    boolean hasMoves();

    Field[][] getFieldsBoard();

}
