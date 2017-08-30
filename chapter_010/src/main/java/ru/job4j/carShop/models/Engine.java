package ru.job4j.carShop.models;

/**
 * Class Engine model.
 * @author agavrikov
 * @since 30.08.2017
 * @version 1
 */
public class Engine {

    /**
     * Id of engine.
     */
    private long id;

    /**
     * Name of engine.
     */
    private String name;

    /**
     * Constructor.
     */
    public Engine() {

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
     * Getter of name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter name.
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }
}