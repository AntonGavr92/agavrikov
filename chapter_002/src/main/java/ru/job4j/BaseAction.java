package ru.job4j;

/**
 * Абстрактный класс, для описания общего у всех действий в меню.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public abstract class BaseAction implements UserAction {
    /**
     * Поле для хранения индекса действия.
     */
    private int key;

    /**
     * Поле для хранения наименования действия.
     */
    private String name;

    /**
     * Конструктор.
     * @param name - наименование действия, отображаемое в меню
     * @param key - Индекс действия, отображающий, нумерацию и одновременно, пункты выбора в меню
     */
    public BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

    /**
     * Метод, возврающий готовую строку для меню.
     * @return полная строка меню
     */
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }

    /**
     * Метод, возвращающий индекс пункта меню.
     * @return индекс пункта меню
     */
    public int key() {
        return this.key;
    }

}
