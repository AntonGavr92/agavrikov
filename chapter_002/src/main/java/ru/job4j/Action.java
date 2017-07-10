package ru.job4j;

/**
 * Интерфейс, определяющий выполнение действия в трекере заявок.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public interface Action {
    /**
     * Метод, с помощью которого происходит выполнение действия в трекере.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    void execute(Tracker tracker, ConsoleInput input);
}
