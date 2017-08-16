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
            char[] symb = new char[]{};
            int curIndex = -1;
            while (true) {
                boolean hasWord = false;
                int val = br.read();
                if (val != -1) {
                    for (String s : abuse) {
                        curIndex = -1;
                        symb = new char[s.length()];
                        for (char c : s.toCharArray()) {
                            if (c == (char) val) {
                                symb[++curIndex] = c;
                                if (curIndex < symb.length - 1) {
                                    val = br.read();
                                }
                                hasWord = true;
                            } else {
                                hasWord = false;
                                break;
                            }
                        }
                        if (hasWord) {
                            break;
                        }
                    }
                    if (curIndex != -1 && !hasWord) {
                        for (char c : symb) {
                            if ((byte) c != 0) {
                                out.write((byte) c);
                            }
                        }
                    } else if (!hasWord) {
                        out.write((byte) (char) val);
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
