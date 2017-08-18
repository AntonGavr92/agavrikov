package ru.job4j.io.socket.file_manager;

/**
 * Created by gavrikov.a on 18/08/2017.
 */
public abstract class Action implements FileManagerAction{

    private final String command;

    private static final String LN = System.lineSeparator();

    public Action(String command) {
        this.command = command;
    }

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
