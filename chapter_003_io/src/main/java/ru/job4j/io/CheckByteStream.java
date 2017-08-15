package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Класс, для проверки входящего потока.
 * @author agavrikov
 * @since 15.08.2017
 * @version 1
 */
public class CheckByteStream {

    /**
     * Метод для определения, является ли входящий потоко данных четным числом.
     * @param in входящий поток данных
     * @return true - если число четное, иначе - false
     */
    boolean isNumber(InputStream in) {
        boolean result = false;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            int number = Integer.parseInt(br.readLine());
            if (number % 2 == 0) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException  nfe) {
            nfe.printStackTrace();
        }
        return result;
    }
}
