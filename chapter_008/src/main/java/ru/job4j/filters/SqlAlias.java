package ru.job4j.filters;

/**
 * Класс описывающий работу с alias.
 * @author agavrikov
 * @since 28.07.2017
 * @version 1
 */
public class SqlAlias {

    /**
     * Поле для хранение пользовательского ввода строки.
     */
    private String userForm;

    /**
     * Поле для хранение alias на sql от userForm.
     */
    private String sqlForm;

    /**
     * Флаг для хранения информации, является ли sqlForm LIKE.
     */
    private boolean isLike;

    /**
     * Конструктор.
     * @param userForm пользовательский ввод
     * @param sqlForm команда на sql
     */
    public SqlAlias(String userForm, String sqlForm) {
        this.userForm = userForm;
        this.sqlForm = sqlForm;
    }

    /**
     * Конструктор.
     * @param userForm пользовательский ввод
     * @param sqlForm команда на sql
     * @param isLike флаг является ли sql команда LIKE
     */
    public SqlAlias(String userForm, String sqlForm, boolean isLike) {
        this.userForm = userForm;
        this.sqlForm = sqlForm;
        this.isLike = isLike;
    }

    /**
     * Геттер.
     * @return поле isLike
     */
    public boolean isLike() {
        return this.isLike;
    }

    /**
     * Геттер.
     * @return поле userForm
     */
    public String getUserForm() {
        return this.userForm;
    }

    /**
     * Геттер.
     * @return поле sqlForm
     */
    public String getSqlForm() {
        return this.sqlForm;
    }

}
