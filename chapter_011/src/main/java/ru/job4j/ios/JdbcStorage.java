package ru.job4j.ios;

import org.springframework.stereotype.Component;

/**
 * Created by gavrikov.a on 31/08/2017.
 */
public class JdbcStorage implements Storage {

    @Override
    public void add(User user) {
        System.out.println(String.format("User %s added in db.", user.getName()));
    }
}
