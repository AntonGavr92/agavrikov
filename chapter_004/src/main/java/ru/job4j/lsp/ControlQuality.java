package ru.job4j.lsp;

import java.util.TreeSet;

/**
 * Class for add food in correct storage.
 * @author agavrikov
 * @since 22.08.2017
 * @version 1
 */
public class ControlQuality {

    /**
     * Structure for storages.
     */
    private TreeSet<Storage> storages = new TreeSet<>();

    /**
     * Default constructor for initialisation storages.
     */
    public ControlQuality() {
        storages.add(new Warehouse(0, 25));
        storages.add(new Shop(25, 99));
        storages.add(new Trash(100, 100));
    }

    /**
     * Constructor for initialisation storages from param.
     * @param storages storages.
     */
    public ControlQuality(TreeSet<Storage> storages) {
        this.storages = storages;
    }


    /**
     * Method for put food in correct storage.
     * @param food food
     */
    public void putInStorage(Food food) {
        int percentExpiryFood = (int) (100 - (double) (food.getExpirydDate() - System.currentTimeMillis()) / (food.getExpirydDate() - food.getCreateDate()) * 100);
        if (percentExpiryFood > 100) {
            percentExpiryFood = 100;
        }
        if (percentExpiryFood > 75 && percentExpiryFood < 100) {
            food.setDiscount(50);
        }
        for (Storage storage : this.storages) {
            if (percentExpiryFood >= storage.getPercentExpiryControlFrom() && percentExpiryFood <= storage.getPercentExpiryControlTo()) {
                storage.putFood(food);
                break;
            }
        }
    }

    /**
     * Getter storages.
     * @return storages
     */
    public TreeSet<Storage> getStorages() {
        return this.storages;
    }
}
