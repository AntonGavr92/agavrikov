package ru.job4j.task.dao;

import java.util.ArrayList;

/**
 * Интерфейс, описывающий методы для работы с бд по каждой таблице.
 * @author agavrikov
 * @since 10.08.2017
 * @version 1
 * @param <T> - сущность
 */

public interface EntityDao<T> {

    /**
     * Метод для получения сущности из бд по идентификатору.
     * @param id идентификатор
     * @return сущность
     */
    T getById(int id);

    /**
     * Метод для добавления сущности в бд.
     * @param entity сущность
     * @return идентификатор сущности в бд
     */
    int add(T entity);

    /**
     * Метод для получения всех сущностей из бд.
     * @return список сущностей.
     */
    ArrayList<T> getAllEntities();

    /**
     * Метод для удаление сущности из бд.
     * @param entity сущность
     */
    void deleteEntity(T entity);

    /**
     * Метод для редактирования сущности в бд.
     * @param entity сущность
     */
    void updateEntity(T entity);
}
