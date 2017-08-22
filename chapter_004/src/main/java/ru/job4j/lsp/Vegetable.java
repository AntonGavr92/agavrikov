package ru.job4j.lsp;

/**
 * Created by gavrikov.a on 22/08/2017.
 */
public class Vegetable extends Food {
    /**
     * Constructor for initialisation fields.
     *
     * @param name        name
     * @param expirydDate expirydDate
     * @param createDate  createDate
     * @param price       price
     */
    public Vegetable(String name, long expirydDate, long createDate, int price) {
        super(name, expirydDate, createDate, price);
    }
}
