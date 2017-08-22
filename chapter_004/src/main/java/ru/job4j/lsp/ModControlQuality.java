package ru.job4j.lsp;

import java.util.LinkedList;

/**
 * Created by gavrikov.a on 22/08/2017.
 */
public class ModControlQuality extends ControlQuality {

    private LinkedList<ReproductFood> reproductFoods = new LinkedList<>();

    private VegetableStorage vs = new VegetableStorage(0, 99);

    public ModControlQuality() {
        this.getStorages().add(new VegetableStorage(0, 99));
    }

    public void addStorage(Storage storage) {
        this.getStorages().add(storage);
    }

    public void putInStorage(ReproductFood food) {
        int percentExpiryFood = (int) (100 - (double) (food.getExpirydDate() - System.currentTimeMillis()) / (food.getExpirydDate() - food.getCreateDate()) * 100);
        if (percentExpiryFood >= 100 && food.isCanReproduct()) {
            reproductFoods.add(food);
        } else {
            super.putInStorage(food);
        }
    }

    public void putInStorage(Vegetable vegetable) {
        int percentExpiryFood = (int) (100 - (double) (vegetable.getExpirydDate() - System.currentTimeMillis()) / (vegetable.getExpirydDate() - vegetable.getCreateDate()) * 100);
        if (percentExpiryFood <= vs.getPercentExpiryControlTo()) {
            vs.putFood(vegetable);
        }
    }

}
