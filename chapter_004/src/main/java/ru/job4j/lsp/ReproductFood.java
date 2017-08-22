package ru.job4j.lsp;

/**
 * Created by gavrikov.a on 22/08/2017.
 */
public class ReproductFood extends Food {

    private boolean canReproduct = true;

    /**
     * Constructor for initialisation fields.
     *
     * @param name        name
     * @param expirydDate expirydDate
     * @param createDate  createDate
     * @param price       price
     */
    public ReproductFood(String name, long expirydDate, long createDate, int price) {
        super(name, expirydDate, createDate, price);
    }

    /**
     * Constructor for initialisation fields.
     *
     * @param name        name
     * @param expirydDate expirydDate
     * @param createDate  createDate
     * @param price       price
     */
    public ReproductFood(String name, long expirydDate, long createDate, int price, boolean isReproducted) {
        super(name, expirydDate, createDate, price);
        this.canReproduct = isReproducted;
    }

    public boolean isCanReproduct() {
        return this.canReproduct;
    }
}
