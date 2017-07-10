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
}
