package ru.job4j.ios;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * Created by gavrikov.a on 31/08/2017.
 */
@Component
public class MemoryStorage implements Storage{

    private final LinkedList<User> storage = new LinkedList<>();

    @Override
    public void add(User user) {
        this.storage.add(user);
    }
}
