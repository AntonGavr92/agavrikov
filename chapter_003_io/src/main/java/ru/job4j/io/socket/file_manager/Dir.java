package ru.job4j.io.socket.file_manager;

import java.io.File;

/**
 * Класс, опиисывающий курсор на текущую директорию на сервере.
 * @author agavrikov
 * @since 18.08.2017
 * @version 1
 */
public class Dir {

    /**
     * Текущий путь на сервере.
     */
    private String path;

    /**
     * новый путь на сервере к директории или  файлу.
     */
    private String newPath;

    /**
     * Конструктор для инициализации пути на сервере.
     * @param path
     */
    public Dir(String path) {
        this.path = path;
    }

    /**
     * Геттер текущего пути.
     * @return текущий путь
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Сеттер для установки текущей директории на сервере.
     * @param path путь.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Сеттер новой директории или файла на сервере.
     * @param newPath новый путь к директории или файлу на сервере.
     */
    public void setNewPath(String newPath) {
        File file = new File(newPath);
        if(file.isDirectory()) {
            this.newPath = String.format("%s/",newPath);
        } else {
            this.newPath = newPath;
        }
    }

    /**
     * Геттер пути к новой директории или файлу.
     * @return новый путь.
     */
    public String getNewPath() {
        return this.newPath;
    }

    /**
     * Метод для преобразования текущего пути на сервере к обновленному.
     */
    public void updatePath () {
        this.path = this.newPath;
    }

}
