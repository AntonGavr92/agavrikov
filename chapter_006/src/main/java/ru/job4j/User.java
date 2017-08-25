package ru.job4j;

/**
 * Created by gavrikov.a on 25/08/2017.
 */
public class User {

    //title 8 bytes

    //normalization 8 : 4 bytes because all object size % 8 = 0

    private String name; // 24 bytes - empty String + char[] 12 byte - title + char(2 bytes) + normalization SString and char[]

    private int age; // 4 bytes

    public User(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public void finalize(){
        System.out.println("destructor");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) {
            User user = new User(String.format("user-%s", i), i + 10);
            System.out.println("Created new user without link on object.");
        }
    }
}
