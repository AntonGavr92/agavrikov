package ru.job4j.task;

/**
 * Created by gavrikov.a on 24/08/2017.
 */
public class EmptyMark implements Mark {

    private final char view;


    public EmptyMark(char symbol) {
        this.view = symbol;

    }

    @Override
    public char getView() {
        return this.view;
    }
}
