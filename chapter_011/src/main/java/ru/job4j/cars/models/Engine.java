package ru.job4j.cars.models;

import javax.persistence.*;

/**
 * Class Engine model.
 * @author agavrikov
 * @since 30.08.2017
 * @version 1
 */
@Entity
@Table(name = "engine")
public class Engine {

    /**
     * Id of engine.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Name of engine.
     */
    @Column(name = "name")
    private String name;

    /**
     * Constructor.
     */
    public Engine() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Engine engine = (Engine) o;

        return name.equals(engine.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Constructor.
     */
    public Engine(int id) {
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