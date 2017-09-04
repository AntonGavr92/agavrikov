package ru.job4j.cars.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.job4j.cars.models.*;

import java.util.List;

/**
 * Created by gavrikov.a on 30/08/2017.
 */
@Component
public class CarsRep {

    private static final String NAME_ENTITY_CAR = "Car";
    private static final String NAME_ENTITY_ENGINE = "Engine";
    private static final String NAME_ENTITY_GEAR_SHIFT = "GearShift";
    private static final String NAME_ENTITY_TRANSMISSION = "Transmission";


    private SessionFactory factory;

    private CarsRep() {

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }


    public void addCar(Car car) {
        try {
            Session session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(car);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
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

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        CarDataRepository carsRep = context.getBean(CarDataRepository.class);
    }
}
