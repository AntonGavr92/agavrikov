package ru.job4j;

import java.util.Arrays;


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
    private Item[] items = new Item[100];

    /**
     * Индекс для добавления в массив. Инкрементируется при добавлении элемента и декрементируется - при удалении.
     */
    private int itemIndex = 0;

    /**
     * Метод для добавления заявки в трекер.
     * @param item - заявка
     * @return - добавленная заявка
     */
    public Item add(Item item) {
        if (itemIndex < this.items.length) {
            this.items[itemIndex] = item;
            itemIndex++;
        }
        return item;
    }

    /**
     * Метод для обновления заявки.
     * @param item заявка, которую нужно обновить
     */
    public void update(Item item) {
        int index = getIndexItemByItem(item);
        if (index >= 0) {
            this.items[index] = item;
        }
    }

    /**
     * Метод для удаления заявки.
     * @param item - заявка, которую нужно удалить
     */
    public void delete(Item item) {
        int index = getIndexItemByItem(item);
        if (index >= 0) {
            this.items[index] = null;
            System.arraycopy(this.items, index + 1, this.items, index, this.items.length - 1 - index);
            itemIndex--;
        }
    }

    /**
     * Метод для получения индекса заявки в массиве items.
     * @param item - заявка
     * @return мндекс элемента в массиве items
     */
    private int getIndexItemByItem(Item item) {
        int indexItem = -1;
        for (int i = 0; i < itemIndex; i++) {
            if (this.items[i].getId().equals(item.getId())) {
                indexItem = i;
                break;
            }
        }
        return indexItem;
    }

    /**
     * Метод для поиска и отображения всех текущих заявок.
     * @return массив всех заявок
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, itemIndex);
    }

    /**
     * Метод для поиска заявок по имени.
     * @param key - имя для поиска
     * @return массив заявок с именем в переменной key
     */
    public Item[] findByName(String key) {
        Item[] foundItems = new Item[this.items.length];
        int currentIndex = 0;
        for (int i = 0; i < itemIndex; i++) {
           if (this.items[i].getName().equals(key)) {
               foundItems[currentIndex] = this.items[i];
               currentIndex++;
           }
        }
        return Arrays.copyOf(foundItems, currentIndex);
    }

    /**
     * Метод для поиска заявок по уникальному идентификатору.
     * @param id - уникальный идентификатор
     * @return - заявка или null, если заявки стаким идентификатором нет.
     */
    public Item findById(String id) {
        Item foundElement = null;
        for (int i = 0; i < itemIndex; i++) {
            if (this.items[i].getId().equals(id)) {
                foundElement = this.items[i];
            }
        }
        return foundElement;
    }

}
