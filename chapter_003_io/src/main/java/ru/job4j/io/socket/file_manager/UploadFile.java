package ru.job4j.io.socket.file_manager;

import java.io.*;

/**
 * Класс, реализующий команду загрузки файла на сервер.
 * @author agavrikov
 * @since 18.08.2017
 * @version 1
 */
public class UploadFile extends Action{
    /**
     * Команда.
     */
    private static final String COMMAND = "upload";

    /**
     * Конструктор для инициализации.
     */
    public UploadFile() {
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
            PrintWriter pw = new PrintWriter(out, true);
            String[] pathArr = path.getNewPath().split("/");
            String name = pathArr[pathArr.length - 1];
            File file = new File(String.format("%s%s",path.getPath(), name));
            FileOutputStream fos = new FileOutputStream(file);
            pw.write(String.format("fileIsReady%s", this.getLN()));
            pw.println();
            int b;
            while ((b = in.read()) != -1) {
                fos.write((char) b);
            }
            fos.close();
        } catch (IOException e) {
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
