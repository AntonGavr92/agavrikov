package ru.job4j.generic;

/**
 * Class реализующий хранилище Ролей.
 * @author agavrikov
 * @since 17.07.2017
 * @version 1
 */
public class RoleStore extends SimpleStore<Role> {

    /**
     * Конструктор для инициализации.
     * @param size размер хранилища.
     */
    public RoleStore(int size) {
        super(size);
    }
}
