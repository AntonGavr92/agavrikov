package ru.job4j.generic;

/**
 * Абстрактный класс описывающий магазин.
 * @author agavrikov
 * @since 21.07.2017
 * @version 1
 */
abstract public class SimpleStore<T extends Base> implements Store<T> {
    /**
     * Поле для хранения структуры - списка пользователей.
     */
    private SimpleArray<T> array;

    /**
     * Конструктор для инициализации.
     * @param size размер хранилища.
     */
    public SimpleStore(int size) {
        this.array = new SimpleArray<T>(size);
    }

    /**
     * геттер хранилища.
     * @return хранилище пользователей.
     */
    public SimpleArray<T> getArray() {
        return this.array;
    }

    /**
     * Метод для добавления элемента.
     *
     * @param value значение элемента
     */
    @Override
    public void add(T value) {
        this.array.add(value);
    }

    /**
     * метод для изменения элемента.
     *
     * @param elem
     * @param value элементкоторым заменяем текущий элемент.
     * @elem элемент, который нужно изменить.
     */
    @Override
    public void update(T elem, T value) {
        this.array.update(this.array.findIndexByObject(elem), value);
    }

    /**
     * Метод для удаления элемента.
     *
     * @param value элемент, который нужно удалить
     */
    @Override
    public void delete(T value) {
        this.array.delete(this.array.findIndexByObject(value));
    }
}
