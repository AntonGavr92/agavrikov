package ru.job4j;

/**
 * Class, описывающий удаление заявки.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class ActionDelete implements Action {

    /**
     * Метод, реализующий удаление заявки.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public void execute(Tracker tracker, ConsoleInput input) {
        input.print("1. Search task by id.");
        input.print("2. Search task by name.");
        input.print("3. Show all tasks.");
        //input.print("4. Back to main menu.");
        int menuItem = Integer.parseInt(input.ask("Select: "));
        if (menuItem == 1) {
            String id = input.ask("Enter id: ");
            Item item = tracker.findById(id);
            if (item != null) {
                tracker.delete(item);
            } else {
                input.print("Task with id " + id + " not found;");
            }
        } else if (menuItem == 2) {
            String name = input.ask("Enter name: ");
            Item[] items = tracker.findByName(name);
            if (items.length > 0) {
                for (int i = 0; i < items.length; i++) {
                    input.print(i + 1 + ". Task " + items[i].getId() + " " + items[i].getName());
                }
            } else {
                input.print("Tasks with name " + name + " not found;");
            }
            tracker.delete(items[Integer.parseInt(input.ask("Select: "))]);
        } else if (menuItem == 3) {
            Item[] items = tracker.findAll();
            for (int i = 0; i < items.length; i++) {
                input.print(i + 1 + ". Task " + items[i].getId() + " " + items[i].getName());
            }
            tracker.delete(items[Integer.parseInt(input.ask("Select: ")) - 1]);
        }
    }
}
