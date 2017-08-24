package ru.job4j.task;

/**
 * Created by gavrikov.a on 24/08/2017.
 */
public class SimpleMark implements Mark {

    private final char view;


    public SimpleMark(char symbol) {
        this.view = symbol;

    }

    @Override
    public char getView() {
        return this.view;
    }
}
