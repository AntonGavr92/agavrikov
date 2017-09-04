package ru.job4j.cars.models;

import javax.persistence.*;

/**
 * Class GearShift model.
 * @author agavrikov
 * @since 30.08.2017
 * @version 1
 */
@Entity
@Table(name = "gear_shift")
public class GearShift {

    /**
     * Id of GearShift.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Name of GearShift.
     */
    @Column(name = "name")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GearShift gearShift = (GearShift) o;

        return name.equals(gearShift.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}