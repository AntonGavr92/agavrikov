package ru.job4j.generic;

/**
 * Class реализующий хранилище пользователей.
 * @author agavrikov
 * @since 17.07.2017
 * @version 1
 */
public class UserStore extends SimpleStore<User> {

    /**
     * Конструктор для инициализации.
     * @param size размер хранилища.
     */
    public UserStore(int size) {
        super(size);
    }
}
