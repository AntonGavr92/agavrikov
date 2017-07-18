package ru.job4j.generic;

/**
 * Class реализующий хранилище Ролей.
 * @author agavrikov
 * @since 17.07.2017
 * @version 1
 */
public class RoleStore implements Store {

    /**
     * Поле для хранения структуры - списка пользователей.
     */
    private SimpleArray<Base> array;

    /**
     * Конструктор для инициализации.
     * @param size размер хранилища.
     */
    public RoleStore(int size) {
        this.array = new SimpleArray<Base>(size);
    }

    /**
     * геттер хранилища.
     * @return хранилище ролей.
     */
    public SimpleArray<Base> getArray() {
        return this.array;
    }

    /**
     * Метод для добавления элемента.
     *
     * @param value значение элемента
     */
    @Override
    public void add(Base value) {
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
    public void update(Base elem, Base value) {
        this.array.update(this.array.findIndexByObject(elem), value);
    }

    /**
     * Метод для удаления элемента.
     *
     * @param value элемент, который нужно удалить
     */
    @Override
    public void delete(Base value) {
        this.array.delete(this.array.findIndexByObject(value));
    }
}
