package ru.job4j.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.models.Redirect;

/**
 * Created by gavrikov.a on 07/09/2017.
 */
public interface RedirectRep extends CrudRepository<Redirect, Long> {
}
