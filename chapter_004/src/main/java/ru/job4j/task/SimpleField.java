package ru.job4j.task;

/**
 * SimpleField class.
 * @author agavrikov
 * @since 28.08.2017
 * @version 1
 */
public class SimpleField {

    /**
     * Mark on field.
     */
    public final SimpleMark mark;

    /**
     * Field for flag empty field.
     */
    private final boolean isEmpty;

    /**
     * Constructor.
     * @param mark mark.
     */
    public SimpleField(SimpleMark mark) {
        this.mark = mark;
        this.isEmpty = false;
    }

    /**
     * Constructor.
     * @param mark mark
     * @param isEmpty field is empty.
     */
    public SimpleField(SimpleMark mark, boolean isEmpty) {
        this.mark = mark;
        this.isEmpty = isEmpty;
    }

    /**
     * Change Empty field on field with mark.
     * @param mark mark
     * @return new field with mark.
     */
    public SimpleField changeMark(SimpleMark mark) {
        SimpleField sf = null;
        if (this.isEmpty()) {
            sf = new SimpleField(mark);
        }
        return sf;
    }

    /**
     * method for check field on empty.
     * @return if field is empty - true, else false.
     */
    public boolean isEmpty() {
        return this.isEmpty;
    }

}
