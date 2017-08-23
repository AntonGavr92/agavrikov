package ru.job4j.io.task;

import java.io.File;

/**
 * Created by gavrikov.a on 23/08/2017.
 */
public class Validator {

    /**
     * Символ, идентифицирующий поиск осуществляемый по маске.
     */
    private static final String TYPE_SEARCH_BY_MASK = "-m";

    /**
     * Символ маски.
     */
    private static final String MASK_SYMBOl = "*";

    /**
     * Символ, идентифицирующий поиск осуществляемый по полному наименованию.
     */
    private static final String TYPE_SEARCH_BY_FULL_NAME = "-f";

    /**
     * Тип валидации.
     */
    private String type;

    /**
     * Искомое значение.
     */
    private String searchVal;

    /**
     * Конструктор для инициализации.
     * @param type тип поиска
     * @param searchVal искомое значение
     */
    public Validator(String type, String searchVal) {
        this.type = type;
    }

    /**
     * Метод для проверки имени файла на наличие искомого значения в зависимости от типа поиска.
     * @param file файл для проверки
     * @return true, если файл удовлетворяет условиям иначе false.
     */
    public boolean isCorrect(File file) {
        boolean result = false;
        if (type.equals(TYPE_SEARCH_BY_MASK)) {
            result = this.checkByMask(file.getName());
        } else if (type.equals(TYPE_SEARCH_BY_FULL_NAME)) {
            result = this.checkByFullName(file.getName());
        }
        return result;
    }

    /**
     * Метод для проверки имени по маске.
     * @param fileName имя файла
     * @return если имя файла соответствует маске - true иначе false
     */
    private boolean checkByMask(String fileName) {
        boolean result = false;
        if (this.searchVal.equals(MASK_SYMBOl)) {
            result = true;
        } else if (fileName.contains(this.searchVal.replace(MASK_SYMBOl, ""))) {
            result = true;
        }
        return result;
    }

    /**
     * Метод для проверки имени.
     * @param fileName имя файла
     * @return если имя файла идеентично искомому - true иначе false
     */
    private boolean checkByFullName(String fileName) {
        boolean result = false;
        if (this.searchVal.equals(fileName)) {
            result = true;
        }
        return result;
    }
}
