package ru.job4j.io.socket.file_manager;

import java.io.InputStream;
import java.io.File;
import java.io.OutputStream;

/**
 * Класс, реализующий команду перехода в вышестоящую директорию.
 * @author agavrikov
 * @since 18.08.2017
 * @version 1
 */
public class UpDir extends Action{
    /**
     * Команда.
     */
    private static final String COMMAND = "up";

    /**
     * Конструктор для инициализации.
     */
    public UpDir() {
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
        File folder = new File(String.format("%s%s", path, "../"));
        /*if(folder.exists() && folder.isDirectory()) {
            this.getCurDir().setPath(folder.getPath());
        }*/
    }

    /**
     * Метод для получения описания команды.
     *
     * @return описание команды
     */
    @Override
    public String getDescr() {
        return "Command change current directory to up directory";
    }
}