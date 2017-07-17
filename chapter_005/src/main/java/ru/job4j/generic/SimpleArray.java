package ru.job4j.generic;

/**
 * Class реализующий обобщения.
 * @author agavrikov
 * @since 17.07.2017
 * @version 1
 * @param <T> - тип элементов в нашей структуре
 */
public class SimpleArray<T> {

    /**
     * Поле для хранения массива элементов.
     */
    private Object[] objects;

    /**
     * Поле для текущего индекса.
     */
    private int index = 0;

    /**
     * КОнструктор.
     * @param size - размер массива.
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * Метод для добавления элемента в нашу структуру.
     * @param val значение, которое необходимо добавить
     */
    public void add(T val) {
        objects[this.index++] = val;
    }

    /**
     * Метод, с помощью которого мы можем получить элемент по индексу.
     * @param index индекс элемента в нашей структуре
     * @return объект по индексу
     */
    public T get(int index) {
        return (T) this.objects[index];
    }

    /**
     * Метод для удаления элемента по его индексу.
     * @param index индекс элемента в нашей структуре
     */
    public void delete(int index) {
        this.objects[index] = null;
        System.arraycopy(this.objects, index + 1, this.objects, index, this.objects.length - 1 - index);
        this.index--;
    }

    /**
     * Метод для изменения элемента по индексу.
     * @param index индекс элемента
     * @param val новое значение
     */
    public void update(int index, T val) {
        this.objects[index] = val;
    }

}
