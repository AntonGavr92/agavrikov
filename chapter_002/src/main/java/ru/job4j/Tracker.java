package ru.job4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


/**
 * Class реализующий трекер заявок.
 * @author agavrikov
 * @since 08.07.2017
 * @version 1
 */
public class Tracker {

    /**
     * Заявки трекера.
     */
    private ArrayList<Item> items = new ArrayList<Item>();


    /**
     * Метод для добавления заявки в трекер.
     * @param item - заявка
     * @return - добавленная заявка
     */
    public Item add(Item item) {
        this.items.add(item);
        return item;
    }

    /**
     * Метод для обновления заявки.
     * @param item заявка, которую нужно обновить
     */
    public void update(Item item) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(item.getId())) {
                this.items.set(i, item);
            }
        }
    }

    /**
     * Метод для удаления заявки.
     * @param item - заявка, которую нужно удалить
     */
    public void delete(Item item) {
        this.items.remove(item);
    }


    /**
     * Метод для поиска и отображения всех текущих заявок.
     * @return массив всех заявок
     */
    public ArrayList<Item> findAll() {
        return this.items;
    }

    /**
     * Метод для поиска заявок по имени.
     * @param key - имя для поиска
     * @return массив заявок с именем в переменной key
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> foundItems = new ArrayList<Item>();
        int currentIndex = 0;
        for (Item item : this.items) {
           if (item.getName().equals(key)) {
               foundItems.add(item);
           }
        }
        return foundItems;
    }

    /**
     * Метод для поиска заявок по уникальному идентификатору.
     * @param id - уникальный идентификатор
     * @return - заявка или null, если заявки стаким идентификатором нет.
     */
    public Item findById(String id) {
        Item foundElement = null;
        for (Item item : this.items) {
            if (item.getId().equals(id)) {
                foundElement = item;
            }
        }
        return foundElement;
    }

}
