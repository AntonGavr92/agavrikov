package ru.job4j.generic;

/**
 * Class реализующий обобщения.
 * @author agavrikov
 * @since 17.07.2017
 * @version 1
 * @param <T> - тип элементов в нашей структуре
 */
public interface Store<T extends Base> {
    /**
     * Метод для добавления элемента.
     * @param value значение элемента
     */
    void add(T value);

    /**
     * метод для изменения элемента.
     * @param value элементкоторым заменяем текущий элемент.
     * @param elem элемент, который нужно изменить.
     */
    void update(T elem, T value);

    /**
     * Метод для удаления элемента.
     * @param value элемент, который нужно удалить
     */
    void delete(T value);
}
