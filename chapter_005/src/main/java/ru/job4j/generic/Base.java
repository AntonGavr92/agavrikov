package ru.job4j.generic;

/**
 * Class абстрактный для задачи по обобщениям.
 * @author agavrikov
 * @since 17.07.2017
 * @version 1
 */
public abstract class Base {

    /**
     * Метод для установки идентификатора.
     * @param id идентификатор
     */
    abstract void setId(String id);

    /**
     * Метод для получения идентификатора.
     * @return идентификатор
     */
    abstract String getId();
}
