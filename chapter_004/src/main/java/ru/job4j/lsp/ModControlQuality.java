package ru.job4j.lsp;

import java.util.LinkedList;

/**
 * ModControlQuality class.
 * @author agavrikov
 * @since 22.08.2017
 * @version 1
 */
public class ModControlQuality extends ControlQuality {

    /**
     * list of reproductFoods.
     */
    private LinkedList<ReproductFood> reproductFoods = new LinkedList<>();

    /**
     * Storage for Vegetable.
     */
    private VegetableStorage vs = new VegetableStorage(0, 99);

    /**
     * Constructor.
     */
    public ModControlQuality() {
        this.getStorages().add(new VegetableStorage(0, 99));
    }

    /**
     * Method for add storage.
     * @param storage storage
     */
    public void addStorage(Storage storage) {
        this.getStorages().add(storage);
    }

    /**
     * Method for put ReproductFood in storage.
     * @param food ReproductFood
     */
    public void putInStorage(ReproductFood food) {
        int percentExpiryFood = (int) (100 - (double) (food.getExpirydDate() - System.currentTimeMillis()) / (food.getExpirydDate() - food.getCreateDate()) * 100);
        if (percentExpiryFood >= 100 && food.isCanReproduct()) {
            reproductFoods.add(food);
        } else {
            super.putInStorage(food);
        }
    }

    /**
     * Method for put Vegetable in storage.
     * @param vegetable vegetable
     */
    public void putInStorage(Vegetable vegetable) {
        int percentExpiryFood = (int) (100 - (double) (vegetable.getExpirydDate() - System.currentTimeMillis()) / (vegetable.getExpirydDate() - vegetable.getCreateDate()) * 100);
        if (percentExpiryFood <= vs.getPercentExpiryControlTo()) {
            vs.putFood(vegetable);
        }
    }

}
