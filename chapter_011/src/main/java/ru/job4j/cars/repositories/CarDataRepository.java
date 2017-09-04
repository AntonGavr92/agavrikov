package ru.job4j.cars.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.models.Car;

/**
 * Created by gavrikov.a on 04/09/2017.
 */
public interface CarDataRepository extends CrudRepository<Car, Integer> {

}
