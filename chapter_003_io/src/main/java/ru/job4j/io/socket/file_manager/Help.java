package ru.job4j.io.socket.file_manager;

import java.io.InputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс, реализующий команду справки по командам.
 * @author agavrikov
 * @since 18.08.2017
 * @version 1
 */
public class Help extends Action {
    /**
     * Команда.
     */
    private static final String COMMAND = "help";

    /**
     * Поле для хранения команд.
     */
    private final List<String> listCommands = new LinkedList<String>();

    /**
     * Конструктор для инициализации.
     *
    */
    public Help(List<FileManagerAction> listCommands) {
        super(COMMAND);
        this.listCommands.add(String.format("Command - Description%s", this.getLN()));
        for (FileManagerAction action : listCommands) {
            this.listCommands.add(String.format("%s - %s%s", action.getCommand(), action.getDescr(), this.getLN()));
        }
    }

    /**
     * Метод для выполнения действия.
     *
     * @param out выходной поток данных
     * @param in  входной поток данных
     * @param path путь к файлу или папке, с которыми нужно произвести действие
     */
    @Override
    public void doAction(OutputStream out, InputStream in, Dir path) {
        PrintWriter pw = new PrintWriter(out, true);
        StringBuilder sb = new StringBuilder();
        for (String command : this.listCommands) {
            sb.append(command);
        }
        pw.write(sb.toString());
        pw.println();
    }

    /**
     * Метод для получения описания команды.
     *
     * @return описание команды
     */
    @Override
    public String getDescr() {
        return "Help";
    }
}