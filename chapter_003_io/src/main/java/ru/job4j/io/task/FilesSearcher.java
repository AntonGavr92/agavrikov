package ru.job4j.io.task;

import java.io.*;

/**
 * Класс, реализующий поиск файлов по указаннному имени или маске в указанной директории.
 * @author agavrikov
 * @since 21.08.2017
 * @version 1
 */
public class FilesSearcher {
    /**
     * Директория для поиска файлов.
     */
    private final String path;

    /**
     * Путь к файлу с логами, при необходимости логгирования.
     */
    private final String resultPath;

    /**
     * Объект - валидатор.
     */
    private Validator validator;

    /**
     * Конструктор с указанным путем к логам, для инициализации полей.
     * @param path путь.
     * @param validator валидатор
     * @param resultPath путь к файлу с логами
     */
    public FilesSearcher(String path, Validator validator, String resultPath) {
        this.path = path;
        this.resultPath = resultPath;
        this.validator = validator;
    }

    /**
     * Конструктор для инициализации полей.
     * @param path путь
     * @param validator валидатор
     */
    public FilesSearcher(String path, Validator validator) {
        this.path = path;
        this.resultPath = null;
        this.validator = validator;
    }

    /**
     * Точка входа в программу.
     * @param args параметры
     */
    public static void main(String[] args) {
        String searchType = "-f";
        String path = "";
        String searchVal = "";
        String logPath = "";
        for (int i = 0; i < args.length; i++) {
            if(args[i].equals("-d")) {
                path = args[i + 1];
            } else if (args[i].equals("-n")) {
                searchVal = args[i + 1];
            } else if (args[i].equals("-o")) {
                logPath = args[i + 1];
            } else if (args[i].equals("-m") || args[i].equals("-f")) {
                searchType = args[i];
            }
        }
        if (!path.equals("") && !searchVal.equals("")) {
            Validator validator = new Validator(searchType, searchVal);
            FilesSearcher filesSearcher;
            if (!logPath.equals("")) {
                filesSearcher = new FilesSearcher(path, validator, logPath);
            } else {
                filesSearcher = new FilesSearcher(path, validator);
            }
            filesSearcher.searchFiles(new File(filesSearcher.path), searchType);
        } else {
            System.out.println("Path and search value must be not empty!");
        }
    }



    /**
     * Метод для формирования файла с логами.
     * @param path путь к файлу с логами.
     */
    private void createLog(String path) {
        try (FileWriter fos = new FileWriter(resultPath, true)) {
            fos.append(String.format("%s%s", path, System.lineSeparator()));
            fos.flush();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * Метод, для определения необходимой функции проверки и вызывающий функцию записи лога.
     * @param file файл
     * @param searchType тип поиска
     */
    private void checkFileName(File file, String searchType) {
        boolean accetableFile = validator.isCorrect(file);
        if (accetableFile && resultPath != null) {
            createLog(file.getPath());
        }
    }

    /**
     * Рекурсивный метод, для обхода всех файлов в file.
     * @param file директория или файл для поиска
     * @param searchType тип поиска.
     */
    public void searchFiles(File file, String searchType) {
        if (!file.isDirectory()) {
            checkFileName(file, searchType);
        } else {
            File[] folderEntries = file.listFiles();
            for (File entry : folderEntries) {
                if (!entry.isDirectory()) {
                    checkFileName(entry, searchType);
                } else {
                    searchFiles(entry, searchType);
                }
            }
        }
    }
}
