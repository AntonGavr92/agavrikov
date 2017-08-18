package ru.job4j.io.socket.file_manager;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Интерфейс, описывающий действия с командами для сервера.
 * @author agavrikov
 * @since 18.08.2017
 * @version 1
 */
public interface FileManagerAction {

    /**
     * Метод для выполнения действия.
     * @param out выходной поток данных
     * @param in входной поток данных
     * @param path путь к файлу или папке, с которыми нужно произвести действие
     */
    void doAction(OutputStream out, InputStream in, Dir path);

    /**
     * Метод для получения команды.
     * @return команда для выполнения действия.
     */
    String getCommand();

    /**
     * Метод для получения описания команды.
     * @return описание команды
     */
    String getDescr();

}
