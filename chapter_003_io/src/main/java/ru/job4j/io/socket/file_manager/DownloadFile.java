package ru.job4j.io.socket.file_manager;

import java.io.*;
import java.net.SocketException;

/**
 * Класс, реализующий команду скачивания файла.
 * @author agavrikov
 * @since 18.08.2017
 * @version 1
 */
public class DownloadFile extends Action{
    /**
     * Команда.
     */
    private static final String COMMAND = "download";

    /**
     * Конструктор для инициализации.
     */
    public DownloadFile() {
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
            FileInputStream fis = new FileInputStream(new File(path.getNewPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            out.write(buffer);
        }  catch (IOException e) {
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
        return "Command upload file";
    }
}