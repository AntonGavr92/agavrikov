package ru.job4j;

import java.util.Date;

/** Класс описывающий добавление задачи в трекер.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class ActionAdd implements Action {

    /**
     * Метод, добавляющий новую задачу в трекер.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public void execute(Tracker tracker, ConsoleInput input) {
        Date date = new Date();
        String name = input.ask("Enter the task's name: ");
        String description = input.ask("Enter the task's description: ");

        Item[] items = tracker.findAll();
        Integer id = 0;
        if (items.length > 0) {
            id = Integer.parseInt(items[items.length - 1].getId());
            id++;
        }
        Item resultItem = tracker.add(new Item(id.toString(), name, description, date.getTime()));
        input.print("Task " + resultItem.getName() + " with id " + resultItem.getId() + " was created.");
    }

}
