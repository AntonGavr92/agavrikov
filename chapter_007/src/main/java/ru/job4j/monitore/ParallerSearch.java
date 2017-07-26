package ru.job4j.monitore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


/**
 * Класс реализующий многопоточный поиск строки в файлах.
 * @author agavrikov
 * @since 25.07.2017
 * @version 1
 */
public class ParallerSearch {

    /**
     * Поле для хранения стартовой директории поиска.
     */
    private String root;

    /**
     * Поле для хранения текста, который нужно найти.
     */
    private String text;

    /**
     * Поле для хранения расширений файлов, в которых нужно искать.
     */
    private List<String> exts;

    /**
     * Поле для хранения путей файлов, которые имеют расширение указанное в exts.
     */
    private List<String> paths = new LinkedList<String>();

    /**
     * Поле для хранения путей файлов, которые имеют расширение указанное в exts и содержат искомую строку.
     */
    private Vector<String> pathsResult = new Vector<String>();

    /**
     * Конструктор для инициализации некоторых полей.
     * @param root директория, в которой начинаем поиск.
     * @param text строка которую ищем в файлах.
     * @param exts список расширений файлов в которых ищем.
     */
    public ParallerSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * Метод для запуска поиска.
     * @return список путей файлов, в которых найдена искомая строка.
     */
    public List<String> search() {
        File[] folderEntries = new File(this.root).listFiles();
        searchInDir(folderEntries);
        if (this.paths.size() > 0) {
            this.threadSearchString();
        }
        return this.pathsResult;
    }

    /**
     * Вспомогательный метод, который производит поиск в указанной директории.
     * @param folderEntries директория поиска.
     */
    private void searchInDir(File[] folderEntries) {
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                search(entry);
                continue;
            }
            if (this.exts.contains(getFileExtension(entry.getName()))) {
                this.paths.add(entry.getPath());
            }
        }
    }

    /**
     * Вспомогательный метод, который инициализирует два дополнительных потока, суть которыйх заключается в том, что бы брать половину списка paths,
     * и искать совпадения(каждый поток в своей половине списка).
     */
    private void threadSearchString() {
        int threadCount = this.paths.size() / 2;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < paths.size() / 2; i++) {
                    if (fileContainsString(paths.get(i))) {
                        pathsResult.add(paths.get(i));
                    }
                }
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = paths.size() / 2; i < paths.size(); i++) {
                    if (fileContainsString(paths.get(i))) {
                        pathsResult.add(paths.get(i));
                    }
                }
            }
        });
        thread.start();
        thread1.start();
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Вспомогательный метод, для поиска в поддиректории.
     * @param folder директория.
     */
    private void search(File folder) {
        File[] folderEntries = folder.listFiles();
        searchInDir(folderEntries);
    }

    /**
     * Вспомогательный метод, для поиска строки в файле.
     * @param path путь к файлу
     * @return true - если файл содержит строку, false - если нет.
     */
    private boolean fileContainsString(String path) {
        File file = new File(path);
        boolean result = false;
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null) {
            try {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.split(this.text).length > 1 || this.text.equals(line)) {
                        result = true;
                        break;
                    }
                }

            } finally {
                scanner.close();
            }
        }
        return result;
    }

    /**
     * Вспомогательный метод для получения расширения файла.
     * @param mystr путь к файлу
     * @return расширение файла.
     */
    private String getFileExtension(String mystr) {
        int index = mystr.indexOf('.');
        return index == -1 ? null : mystr.substring(index);
    }


    /**
     * Точка входа в программу.
     * @param args параметры при запуске
     */
    public static void main(String[] args) {
        LinkedList<String> exts = new LinkedList<String>();
        exts.add(".txt");
        ParallerSearch parallerSearch = new ParallerSearch("C:\\projects\\agavrikov\\chapter_007\\src\\tmp", "test", exts);
        for (String s : parallerSearch.search()) {
            System.out.println(s);
        }
    }
}
