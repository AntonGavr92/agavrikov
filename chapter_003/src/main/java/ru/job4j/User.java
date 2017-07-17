package ru.job4j;

/**
 * Класс определяющий пользователя.
 * @author agavrikov
 * @since 13.07.2017
 * @version 1
 */
public class User implements Comparable<User> {

    /**
     * Поле для хранения идентификатора пользователя.
     */
    private int id;

    /**
     * Поле для хранения города пользователя.
     */
    private String city;

    /**
     * Поле для хранения идентификатора пользователя.
     */
    private String name;

    /**
     * Поле для хранения возраста пользователя.
     */
    private Integer age;

    /**
     * Конструктор, для инициализации объекта.
     * @param id - id пользователя
     * @param name - имя пользователя
     * @param city - город пользователя
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Конструктор для инициализации пользователя, для задач по сортировке.
     * @param name - имя пользователя
     * @param age - возраст пользователя
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Геттер id.
     * @return идентификатор пользователя
     */
    public int getId() {
        return this.id;
    }

    /**
     * Геттер имени пользователя.
     * @return идентификатор пользователя
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер города пользователя.
     * @return город пользователя
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Геттер для получения возраста.
     * @return возраст
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Реализация метода из Comparable.
     * @param o
     * @return
     */
    @Override
    public int compareTo(User o) {
        if (this.age > o.getAge()) {
            return 1;
        } else if (this.age < o.getAge()) {
            return -1;
        } else {
            return this.age.equals(o.getName()) ? 0 : 1;
        }
    }
}
