package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;


/**
 * Класс, для упорядочивания строк в файле по их длине.
 * @author agavrikov
 * @since 16.08.2017
 * @version 1
 */
public class SortDataFile {

    /**
     * Максимальное кол-во строк в файлах, на которые будет дробится основной файл для внешней сортировки.
     */
    private static final int COUNT_LINES = 2;

    /**
     * Очередь с файлами, в которых отсортированы данные, для слияния в единый файл.
     */
    private Queue<File> sortedFiles = new LinkedList<File>();

    /**
     * Счетчик созданных файлов, для их наименования.
     */
    private int counterTmpFiles = 0;

    /**
     * Структура для сортировки строк каждого файла.
     */
    private TreeSet<String> dataFile = new TreeSet<>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int result = -1;
            if (o1.length() > o2.length()) {
                result = 1;
            }
            return result;
        }
    });

    /**
     * Метод для сортировки данных в файле source и создания файла distance с отсортированным содержимым.
     * @param source файл, содержимое которого необходимо отсортировать.
     * @param distance файл, в который будет помещено отсортированное содержимое
     */
    public void sort(File source, File distance) {
        int counter = 0;
        boolean lastFileCreated = false;
        try {
            RandomAccessFile readFile = new RandomAccessFile(source, "r");
            String line = readFile.readLine();
            while (line != null || !lastFileCreated) {
                if (counter < COUNT_LINES && line != null) {
                    this.dataFile.add(line);
                    counter++;
                    line = readFile.readLine();
                } else {
                    File file = new File(String.format("%s/testTmp%s.txt", source.getParent(), this.counterTmpFiles++));
                    RandomAccessFile addFile = new RandomAccessFile(file, "rw");
                    for (String s : this.dataFile) {
                        addFile.write(String.format("%s%s", s, System.lineSeparator()).getBytes());
                    }
                    if (line == null) {
                        lastFileCreated = true;
                    }
                    sortedFiles.add(file);
                    this.dataFile.clear();
                    counter = 0;
                    addFile.close();
                }
            }
            while (sortedFiles.size() > 1) {
                mergeFiles(this.sortedFiles.poll(), this.sortedFiles.poll());
            }
            File result = this.sortedFiles.poll();
            Files.copy(result.toPath(), distance.toPath());
            result.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для слияния двух файлов в один.
     * @param file1 первый файл
     * @param file2 второй файл
     */
    private void mergeFiles(File file1, File file2) {
        try (RandomAccessFile file1Ac = new RandomAccessFile(file1, "r");
             RandomAccessFile file2Ac = new RandomAccessFile(file2, "r")) {
            String lineF1 = file1Ac.readLine();
            String lineF2 = file2Ac.readLine();
            File file = new File(String.format("%s/Tmp%s.txt", file1.getParent(), this.counterTmpFiles++));
            RandomAccessFile addFile = new RandomAccessFile(file, "rw");
            while (lineF1 != null || lineF2 != null) {
                if (lineF1 == null) {
                    addFile.write(String.format("%s%s", lineF2, System.lineSeparator()).getBytes());
                    lineF2 = file2Ac.readLine();
                } else if (lineF2 == null) {
                    addFile.write(String.format("%s%s", lineF1, System.lineSeparator()).getBytes());
                    lineF1 = file1Ac.readLine();
                } else if (lineF1.length() < lineF2.length()) {
                    addFile.write(String.format("%s%s", lineF1, System.lineSeparator()).getBytes());
                    lineF1 = file1Ac.readLine();
                } else {
                    addFile.write(String.format("%s%s", lineF2, System.lineSeparator()).getBytes());
                    lineF2 = file2Ac.readLine();
                }
            }
            sortedFiles.add(file);
            addFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file1.delete();
        file2.delete();

    }
}
