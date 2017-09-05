package ru.job4j.cars.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.cars.models.User;

/**
 * Created by gavrikov.a on 04/09/2017.
 */
public interface UserDataRepository extends CrudRepository<User, Integer> {

}