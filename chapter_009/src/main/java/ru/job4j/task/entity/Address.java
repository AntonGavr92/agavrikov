package ru.job4j.task.entity;

/**
 * Класс, описывающий адрес пользователя.
 * @author agavrikov
 * @since 14.08.2017
 * @version 1
 */
public class Address {

    /**
     * Идентификатор адреса.
     */
    private int id;

    /**
     * Адрес.
     */
    private String address;

    /**
     * Конструктор для инициализации.
     * @param id идентификатор
     * @param address адрес
     */
    public Address(int id, String address) {
        this.id = id;
        this.address = address;
    }

    /**
     * Конструктор для создания нового адреса.
     * @param address адрес
     */
    public Address(String address) {
        this.address = address;
    }

    /**
     * Геттер идентификатора.
     * @return идентификатор адреса
     */
    public int getId() {
        return id;
    }

    /**
     * Геттер адреса.
     * @return адрес
     */
    public String getAddress() {
        return address;
    }

    /**
     * Сеттер адреса.
     * @param address адрес
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
