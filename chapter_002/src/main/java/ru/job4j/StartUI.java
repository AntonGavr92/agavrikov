package ru.job4j;

/**
 * Class отвечающий за начало работы модуля.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class StartUI {

    /**
     * Поле для хранения объекта, отвечающего за ввод/вывод.
     */
    private Input input;

    /**
     * Поле для хранения объекта трекера.
     */
    private Tracker tracker;

    /**
     * Конструктор.
     * @param input - объект ввода/вывода
     * @param tracker - объект трекер
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод, инициализирующий работу программы.
     */
    public void init() {
        boolean exit = false;

        MenuTracker menu = new MenuTracker();

        while (!exit) {
            for (UserAction menuItem : menu.getActions()) {
                input.print(menuItem.info());
            }
            int indexAction = Integer.parseInt(input.ask("Select: "));
            if (indexAction == 6) {
                exit = true;
            } else {
                menu.select(indexAction).execute(tracker, input);
            }
        }
    }

    /**
     * Точка входа в программу.
     * @param args - аргументы задаваемые при запуске программы
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
