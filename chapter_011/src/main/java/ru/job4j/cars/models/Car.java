package ru.job4j.cars.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * Class car model.
 * @author agavrikov
 * @since 30.08.2017
 * @version 1
 */
@Entity
@Table(name = "car")
public class Car {

    /**
     * Id of car.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Name of car.
     */
    @Column(name = "name")
    private String name;

    /**
     * Engine of car.
     */
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "engine")
    private Engine engine;

    /**
     * Gear shift of car.
     */
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "gear_shift")
    private GearShift gearShift;

    /**
     * Transmission of car.
     */
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "transmission")
    private Transmission transmission;

    @Column(name = "picture_path")
    private String picturePath;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!name.equals(car.name)) return false;
        if (!engine.equals(car.engine)) return false;
        if (!gearShift.equals(car.gearShift)) return false;
        if (!transmission.equals(car.transmission)) return false;
        return picturePath.equals(car.picturePath);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + engine.hashCode();
        result = 31 * result + gearShift.hashCode();
        result = 31 * result + transmission.hashCode();
        result = 31 * result + picturePath.hashCode();
        return result;
    }

    /**
     * Constructor.
     */
    public Car() {


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

    /**
     * Getter of engine.
     * @return engine
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Setter engine.
     * @param engine engine
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * Getter of gear shift.
     * @return gear shift
     */
    public GearShift getGearShift() {
        return gearShift;
    }

    /**
     * Setter gearShift.
     * @param gearShift gearShift
     */
    public void setGearShift(GearShift gearShift) {
        this.gearShift = gearShift;
    }

    /**
     * Getter of transmission.
     * @return id
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * Setter transmission.
     * @param transmission transmission
     */
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
