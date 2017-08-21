package ru.job4j.io.socket.file_manager;

/**
 * Класс, описывающий команду.
 * @author agavrikov
 * @since 18.08.2017
 * @version 1
 */
public abstract class Action implements FileManagerAction{

    /**
     * Поле для хранения команды.
     */
    private final String command;

    /**
     * Поле для хранения разделителя строк.
     */
    private static final String LN = System.lineSeparator();

    /**
     * Конструктор для инициализации команды.
     * @param command команда
     */
    public Action(String command) {
        this.command = command;
    }

    /**
     * геттер разделителя строк.
     * @return
     */
    public String getLN() {
        return LN;
    }

    /**
     * Метод для получения команды.
     *
     * @return команда для выполнения действия.
     */
    @Override
    public String getCommand() {
        return this.command;
    }
}
