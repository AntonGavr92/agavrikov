package ru.job4j.carShop.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.carShop.models.Car;
import ru.job4j.carShop.models.Engine;
import ru.job4j.carShop.models.GearShift;
import ru.job4j.carShop.models.Transmission;


import java.util.List;

/**
 * Created by gavrikov.a on 30/08/2017.
 */
public class CarsRep {

    private static final CarsRep INSTANCE_OF = new CarsRep();

    private static final String NAME_ENTITY_CAR = "Car";
    private static final String NAME_ENTITY_ENGINE = "Engine";
    private static final String NAME_ENTITY_GEAR_SHIFT = "GearShift";
    private static final String NAME_ENTITY_TRANSMISSION = "Transmission";


    private SessionFactory factory;

    private CarsRep() {
        try {
            this.factory = new Configuration().configure("cars.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public static CarsRep instanceOf() {
        return INSTANCE_OF;
    }


    public void addCar(Car car) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(car);
        session.getTransaction().commit();
        session.close();
    }

    public List<Car> getCarsByFilters(String filters){
        Session session = factory.openSession();
        session.beginTransaction();
        List<Car> list = session.createQuery(String.format("from Car where %s", filters)).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public List<Car> getAllCars() {
        return (List<Car>) getAllEntities(NAME_ENTITY_CAR);
    }

    public List<Engine> getAllEngines() {
        return (List<Engine>) getAllEntities(NAME_ENTITY_ENGINE);
    }

    public List<GearShift> getAllGearShift() {
        return (List<GearShift>) getAllEntities(NAME_ENTITY_GEAR_SHIFT);
    }

    public List<Transmission> getAllTransmissions() {
        return (List<Transmission>) getAllEntities(NAME_ENTITY_TRANSMISSION);
    }

    private List<?> getAllEntities(String entity) {
        Session session = factory.openSession();
        session.beginTransaction();
        List<?> list = session.createQuery(String.format("from %s", entity)).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
