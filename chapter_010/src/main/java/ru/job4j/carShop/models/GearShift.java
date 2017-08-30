package ru.job4j.carShop.models;

/**
 * Class GearShift model.
 * @author agavrikov
 * @since 30.08.2017
 * @version 1
 */
public class GearShift {

    /**
     * Id of GearShift.
     */
    private int id;

    /**
     * Name of GearShift.
     */
    private String name;

    /**
     * Constructor.
     */
    public GearShift() {

    }

    /**
     * Constructor.
     */
    public GearShift(int id) {
        this.id = id;
    }

    /**
     * Getter of id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id.
     * @param id id
     */
    public void setId(int id) {
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