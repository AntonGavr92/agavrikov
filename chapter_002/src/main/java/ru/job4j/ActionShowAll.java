package ru.job4j;

/**
 * Class, вывод всех заявок трекера.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class ActionShowAll implements Action {
    /**
     * Метод, выводящий все заявки трекера.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public void execute(Tracker tracker, ConsoleInput input) {
        for (Item item : tracker.findAll()) {
            input.print("Task " +  item.getId() + " name " + item.getName());
        }
    }

}
