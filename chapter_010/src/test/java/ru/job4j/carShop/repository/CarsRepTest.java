package ru.job4j.carShop.repository;

import org.junit.Test;
import ru.job4j.carShop.models.*;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by gavrikov.a on 31/08/2017.
 */
public class CarsRepTest {

    private static final CarsRep CARS_REP = CarsRep.instanceOf();

    private Car createCar(String name) {
        User user = new User();
        user.setLogin("test");
        user.setName(name);
        user.setPassword("test");

        Engine engine = new Engine();
        engine.setName("test Engine");

        Transmission transmission = new Transmission();
        transmission.setName("test Transmission");

        GearShift gs = new GearShift();
        gs.setName("test Gear Shift");

        Car car = new Car();
        car.setName(name);
        car.setPicturePath("test/path");
        car.setGearShift(gs);
        car.setTransmission(transmission);
        car.setUser(user);
        car.setEngine(engine);
        return car;
    }

    @Test
    public void addCar() {
        Car car = createCar("test1");
        CARS_REP.addCar(car);
        assertThat(CARS_REP.getAllCars().contains(car), is(true));
    }

    @Test
    public void getCarsByFilters() {
        Car car = createCar("test3");
        CARS_REP.addCar(car);
        assertThat(CARS_REP.getCarsByFilters("name like '%test3%'").contains(car), is(true));
    }

    @Test
    public void getAllCars() {
        Car car = createCar("test2");
        CARS_REP.addCar(car);
        assertThat(CARS_REP.getAllCars().contains(car), is(true));
    }

    @Test
    public void getAllEngines() {
        Car car = createCar("test5");
        CARS_REP.addCar(car);
        assertThat(CARS_REP.getAllEngines().contains(car.getEngine()), is(true));
    }

    @Test
    public void getAllGearShift() {
        Car car = createCar("test6");
        CARS_REP.addCar(car);
        assertThat(CARS_REP.getAllGearShift().contains(car.getGearShift()), is(true));
    }

    @Test
    public void getAllTransmissions() {
        Car car = createCar("test7");
        CARS_REP.addCar(car);
        assertThat(CARS_REP.getAllTransmissions().contains(car.getTransmission()), is(true));
    }

}