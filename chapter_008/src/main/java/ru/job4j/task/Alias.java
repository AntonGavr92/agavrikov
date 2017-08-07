package ru.job4j.task;

/**
 * Класс описывающий синоним для заданной строки.
 * @author agavrikov
 * @since 02.08.2017
 * @version 1
 */
public class Alias {

    /**
     * поле для хранения слова.
     */
    private String word;

    /**
     * Поле для хранения синонима.
     */
    private String alias;

    /**
     * Конструктор.
     * @param word поле
     * @param alias синоним
     */
    public Alias(String word, String alias) {
        this.word = word;
        this.alias = alias;
    }

    /**
     * Геттер слова.
     * @return слово
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Геттер синонима.
     * @return синоним.
     */
    public String getAlias() {
        return this.alias;
    }
}
