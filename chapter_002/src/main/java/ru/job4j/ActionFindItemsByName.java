package ru.job4j;

/**
 * Class, поиск заявки по id.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class ActionFindItemsByName implements Action {

    /**
     * Метод, для поиска заявки по id и вывода данных по ней пользователю.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public void execute(Tracker tracker, Input input) {
        String name = input.ask("Enter name: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                input.print("Found item - " + item.getId() + " " + item.getName());
            }
        } else {
            input.print("Item with name - " + name + " not found");
        }

    }
}
