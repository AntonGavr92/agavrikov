package ru.job4j;

/**
 * Class отвечающий за начало работы модуля.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class StartUI {
    /**
     * Метод, инициализирующий работу программы.
     */
    public void init() {
        boolean exit = false;
        ConsoleInput input = new ConsoleInput();
        Tracker tracker = new Tracker();

        String[] menuItems = {"0. Add new Item",
                "1. Show all items",
                "2. Edit item",
                "3. Delete item",
                "4. Find item by Id",
                "5. Find items by name",
                "6. Exit Program"};

        Action[] actions = {new ActionAdd(),
                new ActionShowAll(),
                new ActionEditItem(),
                new ActionDelete(),
                new ActionFindItemById(),
                new ActionFindItemsByName()};

        MenuTracker menu = new MenuTracker(menuItems, actions);

        while (!exit) {
            for (String menuItem : menu.getMenuItems()) {
                input.print(menuItem);
            }
            int indexAction = Integer.parseInt(input.ask("Select: "));
            if (indexAction < menuItems.length) {
                if (indexAction == 6) {
                    exit = true;
                } else {
                    menu.select(indexAction).execute(tracker, input);
                }
            }
        }
    }

    /**
     * Точка входа в программу.
     * @param args - аргументы задаваемые при запуске программы
     */
    public static void main(String[] args) {
        new StartUI().init();
    }
}
