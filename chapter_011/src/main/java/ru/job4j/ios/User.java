package ru.job4j.ios;

import org.springframework.stereotype.Component;

/**
 * Created by gavrikov.a on 31/08/2017.
 */
@Component
public class User {

    private String name;

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
