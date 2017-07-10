package ru.job4j;

/** Интерфейс, определяющий консольный ввод.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public interface Input {

    /**
     * Метод, для взаимодействия с пользователем. Задает вопрос и возвращает ответ.
     * @param question - вопрос пользователю
     * @return считанную строку ответа пользователя
     */
    String ask(String question);

    /**
     * Метод для вывода данных в консоль.
     * @param data - строка, которую необходимо вывести
     */
    void print(String data);

}
