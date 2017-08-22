package ru.job4j.lsp;

/**
 * Milk class.
 * @author agavrikov
 * @since 22.08.2017
 * @version 1
 */
public class Milk extends Food {

    /**
     * Constructor for initialisation fields.
     * @param name name
     * @param expirydDate expirydDate
     * @param createDate createDate
     * @param price price
     */
    public Milk(String name, long expirydDate, long createDate, int price) {
        super(name, expirydDate, createDate, price);
    }

}
