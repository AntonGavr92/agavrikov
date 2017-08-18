package ru.job4j.io.socket.file_manager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Класс, реализующий команду перехода в директорию.
 * @author agavrikov
 * @since 18.08.2017
 * @version 1
 */
public class ChangeDir extends Action{
    /**
     * Команда.
     */
    private static final String COMMAND = "cd";

    /**
     * Конструктор для инициализации.
     */
    public ChangeDir() {
        super(COMMAND);
    }

    /**
     * Метод для выполнения действия.
     * @param out выходной поток данных
     * @param in входной поток данных
     * @param path путь к файлу или папке, с которыми нужно произвести действие
     */
    @Override
    public void doAction(OutputStream out, InputStream in, Dir path) {
        try {
            File folder = new File(String.format("%s", path.getNewPath()));
            PrintWriter pw = new PrintWriter(out, true);
            if(folder.exists() && folder.isDirectory()) {
                path.updatePath();
                pw.write(String.format("You in %s%s", folder.getPath(), this.getLN()));
                pw.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для получения описания команды.
     *
     * @return описание команды
     */
    @Override
    public String getDescr() {
        return "Command change current directory";
    }
}
