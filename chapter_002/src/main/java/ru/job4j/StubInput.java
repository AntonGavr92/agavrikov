package ru.job4j;

import java.util.ArrayList;

/** Класс описывающий добавление задачи в трекер.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class StubInput implements Input {

    /**
     * Поле для хранения ответов(имитация пользовательского ввода).
     */
    private ArrayList<String> answers;

    /**
     * Поле для хранения индекса ответа.
     */
    private int position = 0;

    /**
     * Конструктор.
     * @param answers - массив ответов(имитация пользовательского ввода).
     */
    public StubInput(ArrayList<String> answers) {
        this.answers = answers;
    }
    /**
     * Метод, для взаимодействия с пользователем. Задает вопрос и возвращает ответ.
     * @param question - вопрос пользователю
     * @return Ответ пользователя
     */
    @Override
    public String ask(String question) {
        return answers.get(position++);
    }

    /**
     * Метод для вывода данных в консоль.
     * @param data - строка, которую необходимо вывести
     */
    @Override
    public void print(String data) {
        System.out.println(data);
    }

    /**
     * Метод для обратки ввода для массива.
     * @param question - вопрос пользователю
     * @param range - длинна массива
     * @return - возвращает индекс в массиве меню
     */
    public int ask(String question, ArrayList<Integer> range) {
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