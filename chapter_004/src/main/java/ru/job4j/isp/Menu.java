package ru.job4j.isp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Class menu.
 * @author agavrikov
 * @since 23.08.2017
 * @version 1
 */
public class Menu implements AddItemMenu, GetItemsMap {

    /**
     * list of items.
     */
    private LinkedList<Item> menuItems = new LinkedList<>();

    /**
     * Method for adding items in menu.
     * @param item item menu.
     */
    @Override
    public void addItem(Item item) {
        this.menuItems.add(item);
    }

    /**
     * Getter.
     * @return list of items.
     */
    public LinkedList<Item> getMenuItems() {
        return this.menuItems;
    }

    /**
     * Method for get all items from menu.
     * @return map of items
     */
    @Override
    public Map<Integer, Item> getItemsMap() {
        Map<Integer, Item> result = new HashMap<>(this.getMenuItems().size());
        getItemsMapRec(this, result);
        return result;
    }

    /**
     * Method for get full tree items.
     * @param menu menu
     * @param result map of items
     */
    private void getItemsMapRec(Menu menu, Map result) {
        for (Item item : menu.getMenuItems()) {
            if (item.isHasSubMenu()) {
                this.getItemsMapRec(item.getSubMenu(), result);
            }
            result.put(item.getId(), item);
        }
    }

}
