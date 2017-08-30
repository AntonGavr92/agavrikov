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
    private long id;

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

    /**
     * Constructor.
     */
    public Car() {

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

}
