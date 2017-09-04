package ru.job4j.cars.models;

import javax.persistence.*;

/**
 * Class Transmission model.
 * @author agavrikov
 * @since 30.08.2017
 * @version 1
 */
@Entity
@Table(name = "transmission")
public class Transmission {

    /**
     * Id of Transmission.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Name of Transmission.
     */
    @Column(name = "name")
    private String name;

    /**
     * Constructor.
     */
    public Transmission() {

    }

    /**
     * Constructor.
     */
    public Transmission(int id) {
        this.id = id;
    }

    /**
     * Getter of id.
     * @return id
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transmission that = (Transmission) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
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
