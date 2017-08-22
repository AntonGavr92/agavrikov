package ru.job4j.lsp;

/**
 * Abstract food class.
 * @author agavrikov
 * @since 22.08.2017
 * @version 1
 */
public abstract class Food {

    /**
     * Name of food.
     */
    private String name;

    /**
     * Date of food expiryd.
     */
    private long expirydDate;

    /**
     * Date of food creation.
     */
    private long createDate;

    /**
     * Price of food.
     */
    private int price;

    /**
     *Discount on food.
     */
    private int discount;

    /**
     * Constructor for initialisation fields.
     * @param name name
     * @param expirydDate expirydDate
     * @param createDate createDate
     * @param price price
     */
    public Food(String name, long expirydDate, long createDate, int price) {
        this.name = name;
        this.expirydDate = expirydDate;
        this.createDate = createDate;
        this.price = price;
    }

    /**
     * Discount setter.
     * @param discount discount
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * Getter ExpirydDate.
     * @return ExpirydDate
     */
    public long getExpirydDate() {
        return this.expirydDate;
    }

    /**
     * Getter getCreateDate.
     * @return CreateDate
     */
    public long getCreateDate() {
        return this.createDate;
    }

}
