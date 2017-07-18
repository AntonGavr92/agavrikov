package ru.job4j.generic;


/**
 * Class реализующий пользователей.
 * @author agavrikov
 * @since 17.07.2017
 * @version 1
 */
public class User extends Base {

    /**
     * Уникальный идентификатор пользователя.
     */
    private String id;

    /**
     * Метод для установки идентификатора.
     *
     * @param id идентификатор
     */
    @Override
    void setId(String id) {
        this.id = id;
    }

    /**
     * Метод для получения идентификатора.
     *
     * @return идентификатор
     */
    @Override
    String getId() {
        return this.id;
    }
}
