package ru.job4j.carShop.models;

/**
 * Class car model.
 * @author agavrikov
 * @since 30.08.2017
 * @version 1
 */
public class Car {

    /**
     * Id of car.
     */
    private int id;

    /**
     * Name of car.
     */
    private String name;

    /**
     * Engine of car.
     */
    private Engine engine;

    /**
     * Gear shift of car.
     */
    private GearShift gearShift;

    /**
     * Transmission of car.
     */
    private Transmission transmission;

    private String picturePath;

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
