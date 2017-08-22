package ru.job4j.lsp;

/**
 * Meat class.
 * @author agavrikov
 * @since 22.08.2017
 * @version 1
 */
public class Meat extends Food {

    /**
     * Constructor for initialisation fields.
     * @param name name
     * @param expirydDate expirydDate
     * @param createDate createDate
     * @param price price
     */
    public Meat(String name, long expirydDate, long createDate, int price) {
        super(name, expirydDate, createDate, price);
    }

}
