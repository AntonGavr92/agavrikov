package ru.job4j.map;

/**
 * Класс определющий пользователя.
 * @author agavrikov
 * @since 19.07.2017
 * @version 1
 */
public class User {
    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Количество детей.
     */
    private int children;

    /**
     * День рождения пользователя.
     */
    private Calendar birthday;

    /**
     * Конструктор.
     * @param name имя пользователя.
     * @param children количество детей у пользователя.
     * @param birthday день рождения пользователя.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (children != user.children) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}
