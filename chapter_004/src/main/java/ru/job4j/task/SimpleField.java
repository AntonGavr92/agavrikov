package ru.job4j.task;

/**
 * Created by gavrikov.a on 24/08/2017.
 */
public class SimpleField implements Field{

    private Mark mark;

    public void setMark(Mark mark) {
        if (this.isEmpty()) {
            this.mark = mark;
        }
    }

    @Override
    public Mark getMark() {
        Mark result = new EmptyMark('_');
        if (!this.isEmpty()) {
            result = this.mark;
        }
        return result;
    }

    public boolean isEmpty() {
        boolean result = false;
        if (this.mark == null) {
            result = true;
        }
        return result;
    }

}
