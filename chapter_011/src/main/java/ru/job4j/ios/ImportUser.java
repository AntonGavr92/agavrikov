package ru.job4j.ios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gavrikov.a on 31/08/2017.
 */
@Component
public class ImportUser {

    private final Storage storage;

    @Autowired
    public ImportUser(Storage storage) {
        this.storage = storage;
    }

    public void addUser(User user) {
        this.storage.add(user);
    }
}
