package ru.job4j.io;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Класс, для удаления слов из потока.
 * @author agavrikov
 * @since 15.08.2017
 * @version 1
 */
public class StreamRemoveWords {

    /**
     * Метод для считывания данных из входящего потока, удаления слов указанных в abuse и помещения результа в поток вывода.
     * @param in входящий поток данных
     * @param out поток вывода
     * @param abuse массив строк, которые необходимо исключить из входящего потока
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String res = br.readLine();
            while (res != null) {
                for (String s : abuse) {
                    res = res.replaceAll(s, "");
                }
                out.write(res.getBytes());
                res = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
