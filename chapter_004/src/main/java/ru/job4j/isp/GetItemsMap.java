package ru.job4j.isp;

import java.util.Map;

/**
 * Interface for create map of items.
 * @author agavrikov
 * @since 23.08.2017
 * @version 1
 */
public interface GetItemsMap {
    /**
     * Method for create map of items.
     * @return map of items
     */
    Map<Integer, Item> getItemsMap();

}
