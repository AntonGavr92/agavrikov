package ru.job4j.lsp;

import org.junit.Test;

import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test ControlQuality class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class ControlQualityTest {

    /**
     * Method for testing method putInStorage with good expiry of food.
     */
    @Test
    public void whenProductWithGoodExpiryPutInStorageThenStorageIsWarehouse() {
        TreeSet<Storage> storages = new TreeSet<>();
        Storage warehouse = new Warehouse(0, 25);
        Storage shop = new Shop(25, 99);
        Storage trash = new Trash(100, 100);
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        ControlQuality cq = new ControlQuality(storages);
        long time = System.currentTimeMillis();
        Milk milk = new Milk("milk ko", time + 1160000, time - 100000, 50);
        checkProductInStorage(cq, warehouse, milk);
    }

    /**
     * Method for testing method putInStorage with normal expiry of food.
     */
    @Test
    public void whenProductWithNormalExpiryPutInStorageThenStorageIsShop() {
        TreeSet<Storage> storages = new TreeSet<>();
        Storage warehouse = new Warehouse(0, 25);
        Storage shop = new Shop(25, 99);
        Storage trash = new Trash(100, 100);
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        ControlQuality cq = new ControlQuality(storages);
        long time = System.currentTimeMillis();
        Milk milk = new Milk("milk ko", time + 30000, time - 100000, 40);
        checkProductInStorage(cq, shop, milk);
    }

    /**
     * Method for testing method putInStorage with bad expiry of food.
     */
    @Test
    public void whenProductWithBadExpiryPutInStorageThenStorageIsTrash() {
        TreeSet<Storage> storages = new TreeSet<>();
        Storage warehouse = new Warehouse(0, 25);
        Storage shop = new Shop(25, 99);
        Storage trash = new Trash(100, 100);
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        ControlQuality cq = new ControlQuality(storages);
        long time = System.currentTimeMillis();
        checkProductInStorage(cq, trash, new Meat("meat ko", time, time - 100000, 60));

    }

    /**
     * Additional method for testing.
     * @param cq object ControlQuality
     * @param expected expected storage
     * @param foodIn food
     */
    private void checkProductInStorage(ControlQuality cq, Storage expected, Food foodIn) {
        cq.putInStorage(foodIn);
        Storage result = null;
        for (Storage storage : cq.getStorages()) {
            for (Food food : storage.getFoods()) {
                if (food == foodIn) {
                    result = storage;
                }
            }
        }
        assertThat(result, is(expected));
    }

}