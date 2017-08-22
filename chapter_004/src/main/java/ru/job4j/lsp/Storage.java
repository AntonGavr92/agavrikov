package ru.job4j.lsp;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstract storage class.
 * @author agavrikov
 * @since 22.08.2017
 * @version 1
 */
public abstract class Storage implements Comparable<Storage> {

    /**
     * Food in storage.
     */
    private final List<Food> foods = new LinkedList<>();

    /**
     * Lower limit of the expiry in percent.
     */
    private final int percentExpiryControlFrom;

    /**
     * Upper limit of the expiry in percent.
     */
    private final int percentExpiryControlTo;

    /**
     * Constructor for initialisation fields.
     * @param percentExpiryControlFrom lower limit of the expiry in percent.
     * @param percentExpiryControlTo upper limit of the expiry in percent.
     */
    public Storage(int percentExpiryControlFrom, int percentExpiryControlTo) {
        this.percentExpiryControlFrom = percentExpiryControlFrom;
        this.percentExpiryControlTo = percentExpiryControlTo;
    }

    /**
     * Getter lower limit of the expiry in percent.
     * @return lower limit of the expiry in percent.
     */
    public int getPercentExpiryControlFrom() {
        return this.percentExpiryControlFrom;
    }

    /**
     * Getter Upper limit of the expiry in percent.
     * @return Upper limit of the expiry in percent.
     */
    public int getPercentExpiryControlTo() {
        return this.percentExpiryControlTo;
    }

    /**
     * Method for put food in storage.
     * @param food food
     */
    public void putFood(Food food) {
        this.foods.add(food);
    }

    /**
     * Method dor get list of food from storage.
     * @return list of food
     */
    public List<Food> getFoods() {
        return this.foods;
    }

    @Override
    public int compareTo(Storage o) {
        int result = -1;
        if (this.percentExpiryControlTo > o.getPercentExpiryControlTo()) {
            result = 1;
        }
        return result;
    }
}
