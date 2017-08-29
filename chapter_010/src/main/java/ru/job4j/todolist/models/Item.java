package ru.job4j.todolist.models;

/**
 * Class item.
 * @author agavrikov
 * @since 29.08.2017
 * @version 1
 */
public class Item {

    /**
     * Id of item.
     */
    private long id;

    /**
     * Description of item.
     */
    private String desc;

    /**
     * Date created of item.
     */
    private long created;

    /**
     * Flag about done/not done item.
     */
    private boolean done;

    /**
     * Constructor.
     */
    public Item() {

    }

    /**
     * Getter of id.
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter id.
     * @param id id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter of description.
     * @return description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter description.
     * @param desc description
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Getter of date create.
     * @return date create
     */
    public long getCreated() {
        return created;
    }

    /**
     * Setter date create.
     * @param created date create
     */
    public void setCreated(long created) {
        this.created = created;
    }

    /**
     * Getter of flag done item.
     * @return flag done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Setter flag done of item.
     * @param done true if item is done, else false.
     */
    public void setDone(boolean done) {
        this.done = done;
    }
}
