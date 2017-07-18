package ru.job4j.list;

/**
 * Интерфейс описывающий контейнер типа Список.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 * @param <E> - тип элементов в нашей структуре
 */
public interface SimpleContainer<E> extends Iterable<E> {
    /**
     * Метод, описывающий добавление в список.
     * @param value объект, помещаемый в список.
     */
    void add(E value);

    /**
     * Метод, для получения объекта по индексу.
     * @param index индекс элемента в списке.
     * @return объект.
     */
    E get(int index);

    /**
     * Получить количество элементов в контейнере.
     * @return количество элементов в контейнере
     */
    int size();
}
