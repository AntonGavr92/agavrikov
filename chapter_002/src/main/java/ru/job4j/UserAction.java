package ru.job4j;

/**
 * Интерфейс, определяющий выполнение действия в трекере заявок(для внутренних классов).
 * @author agavrikov
 * @since 10.07.2017
 * @version 1
 */
public interface UserAction {

    /**
     * Метод, с помощью которого происходит выполнение действия в трекере.
     * @param tracker - трекер
     * @param input - ввод данных
     * @return булево, нужно ли выходить из программы
     */
    boolean execute(Tracker tracker, Input input);

    /**
     * Метод, для возврата индекса действия.
     * @return индекс действия
     */
    int key();

    /**
     * Метод, для получении информации о действии.
     * @return информация о действии в меню
     */
    String info();
}
