package ru.job4j;

import java.util.Scanner;

/**
 * Class отвечающий за пользовательский ввод данных.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class ConsoleInput implements Input {
    /**
     * Поле для хранения введенных данных.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод, для взаимодействия с пользователем. Задает вопрос и возвращает ответ.
     * @param question - вопрос пользователю
     * @return считанную строку ответа
     */
    public String ask(String question) {
        System.out.println(question);
        return this.scanner.nextLine();
    }

    /**
     * Метод для вывода данных в консоль.
     * @param data - данные для вывода
     */
    public void print(String data) {
        System.out.println(data);
    }

    /**
     * Метод для обратки ввода для массива.
     * @param question - вопрос пользователю
     * @param range - длинна массива
     * @return - возвращает индекс в массиве меню
     */
    public int ask(String question, int[] range) {
        int key = Integer.parseInt(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }
    }
}
