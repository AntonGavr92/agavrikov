package ru.job4j;

/**
 * Class, поиск заявки по id.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class ActionFindItemById implements Action {

    /**
     * Метод, для поиска заявки по id и вывода данных по ней пользователю.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public void execute(Tracker tracker, ConsoleInput input) {
        String id = input.ask("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            input.print("Found item - " + item.getId() + " " + item.getName());
        } else {
            input.print("Item with id " + id + " not found.");
        }
    }
}
