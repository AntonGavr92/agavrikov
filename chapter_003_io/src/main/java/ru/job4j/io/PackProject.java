package ru.job4j.io;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Класс, для архивации с сохранением структуры файлов по определенному пути.
 * @author agavrikov
 * @since 17.08.2017
 * @version 1
 */
public class PackProject {

    /**
     * Путь, который нужно архивировать.
     */
    private String path;

    /**
     * Расширения файлов, которые должны быть заархивированы.
     */
    private String[] exts;

    /**
     * Геттер пути.
     * @return путь
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Конструктор.
     * @param path путь
     * @param nameFile имя файла
     * @param exts расширения, которые нужно архивировать
     */
    public PackProject(String path, String nameFile, String[] exts) {
        this.exts = exts;
        this.path = path;
    }

    /**
     * Точка входа в программу.
     * @param args параметры
     */
    public static void main(String[] args) {
        String path = null;
        String[] exts = null;
        String name = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                path = args[i + 1];
            } else if (args[i].equals("-e")) {
                exts = args[i + 1].replace(" ", "").split(",");
            } else if (args[i].equals("-o")) {
                name = args[i + 1];
            }
        }
        PackProject packProject = new PackProject(path, name, exts);

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path + name))) {
            packProject.processZipFilesFromFolder(new File(packProject.getPath()), zout);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Рекрсивный метод, для прохождения файловой структуры и архивации файлов с нужными расширениями.
     * @param folder папка
     * @param zout поток для архивации
     */
    private void processZipFilesFromFolder(File folder, ZipOutputStream zout) {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                processZipFilesFromFolder(entry, zout);
                continue;
            }
            for (String ext : this.exts) {
                if (entry.getName().contains(String.format(".%s", ext))) {
                    try {
                        FileInputStream fis = new FileInputStream(entry);
                        ZipEntry file = new ZipEntry(entry.getPath().replace(this.path, ""));
                        zout.putNextEntry(file);
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        zout.write(buffer);
                        zout.closeEntry();
                        fis.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
