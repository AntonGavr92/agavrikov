package ru.job4j.io.socket.file_manager;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Класс, реализующий команду получения списка файлов.
 * @author agavrikov
 * @since 18.08.2017
 * @version 1
 */
public class GetListFiles extends Action {

    /**
     * Команда.
     */
    private static final String COMMAND = "gCurFiles";

    /**
     * Конструктор для инициализации.
     */
    public GetListFiles() {
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
        PrintWriter pw = new PrintWriter(out, true);
        File folder = new File(path.getPath());
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                pw.write(String.format("%s %s%s","d", entry.getName(), this.getLN()));
            } else {
                pw.write(String.format("%s%s",entry.getName(), this.getLN()));
            }
        }
        pw.println();
    }

    /**
     * Метод для получения описания команды.
     *
     * @return описание команды
     */
    @Override
    public String getDescr() {
        return "Command show directories and files in current directory";
    }
}
