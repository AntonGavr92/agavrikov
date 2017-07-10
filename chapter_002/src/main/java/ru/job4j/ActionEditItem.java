package ru.job4j;

/**
 * Class, описывающий изменение заявки.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class ActionEditItem implements Action {
    /**
     * метод изменяющий выбранную заявку.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public void execute(Tracker tracker, ConsoleInput input) {
        input.print("1. Search task by id.");
        input.print("2. Search task by name.");
        input.print("3. Show all tasks.");
        int menuItem = Integer.parseInt(input.ask("Select: "));
        if (menuItem == 1) {
            String id = input.ask("Enter id: ");
            Item item = tracker.findById(id);
            if (item != null) {
                constructEditMenu(tracker, input, item);
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
                constructEditMenu(tracker, input, items[Integer.parseInt(input.ask("Select: "))]);
            } else {
                input.print("Tasks with name " + name + " not found;");
            }
        } else if (menuItem == 3) {
            Item[] items = tracker.findAll();
            for (int i = 0; i < items.length; i++) {
                input.print(i + 1 + ". Task " + items[i].getId() + " " + items[i].getName());
            }
            constructEditMenu(tracker, input, items[Integer.parseInt(input.ask("Select: ")) - 1]);
        }
    }

    /**
     * Вспомогательный метод, для построения меню рекдактирования заявки.
     * @param tracker трекер
     * @param input объект для работы с вводом на консоль
     * @param item - заявка
     */
    public void constructEditMenu(Tracker tracker, ConsoleInput input, Item item) {
        input.print("1. Edit name");
        input.print("2. Edit description");
        input.print("3. Add comment");
        int menuEdit = Integer.parseInt(input.ask("Select: "));
        if (menuEdit == 1) {
            tracker.update(new Item(item.getId(), input.ask("Enter new name: "),  item.getDesc(), item.getCreated(), item.getComments()));
        } else if (menuEdit == 2) {
            tracker.update(new Item(item.getId(), item.getName(),  input.ask("Enter new description: "), item.getCreated(), item.getComments()));
        } else if (menuEdit == 3) {
            String[] comments = new String[item.getComments().length];
            comments[item.getComments().length - 1] = input.ask("Enter comment: ");
            tracker.update(new Item(item.getId(), item.getName(),  item.getDesc(), item.getCreated(), comments));
        }
    }
}
