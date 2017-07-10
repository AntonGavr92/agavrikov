package ru.job4j;

/** Класс описывающий добавление задачи в трекер.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class StubInput implements Input {

    /**
     * Поле для хранения ответов(имитация пользовательского ввода).
     */
    private String[] answers;

    /**
     * Поле для хранения индекса ответа.
     */
    private int position = 0;

    /**
     * Конструктор.
     * @param answers - массив ответов(имитация пользовательского ввода).
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }
    /**
     * Метод, для взаимодействия с пользователем. Задает вопрос и возвращает ответ.
     * @param question - вопрос пользователю
     * @return Ответ пользователя
     */
    @Override
    public String ask(String question) {
        return answers[position++];
    }

    /**
     * Метод для вывода данных в консоль.
     * @param data - строка, которую необходимо вывести
     */
    @Override
    public void print(String data) {
        System.out.println(data);
    }
}