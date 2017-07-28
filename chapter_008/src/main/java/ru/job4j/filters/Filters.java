package ru.job4j.filters;

import java.util.ArrayList;

/**
 * Класс описывающий работу с фильтрами sql.
 * @author agavrikov
 * @since 28.07.2017
 * @version 1
 */
public class Filters {

    /**
     * Поле для хранения фильтров.
     */
    private ArrayList<SimpleFilter> filters = new ArrayList<SimpleFilter>();

    /**
     * Поле для хранения словаря синонимов(пользовательский ввод - sql).
     */
    private ArrayList<SqlAlias> aliasDictionary = new ArrayList<SqlAlias>();

    /**
     * Поле для alias таблицы, к которой рисуем запросы.
     */
    private static final String TABLE_ALIAS = "i";


    /**
     * Конструктор по умолчанию.
     * заполняет данными структуру алиасов.
     */
    public Filters() {
        //сопоставление условий пользовательского ввода и sql
        aliasDictionary.add(new SqlAlias(" должно содержать строку ", " LIKE ", true));
        aliasDictionary.add(new SqlAlias(" больше ", " > "));
        aliasDictionary.add(new SqlAlias(" меньше ", " < "));
        aliasDictionary.add(new SqlAlias(" равно ", " = "));
        aliasDictionary.add(new SqlAlias(" и ", " AND "));
        aliasDictionary.add(new SqlAlias(" или ", " OR "));

        //сопоставление полей пользовательского ввода и sql при необходимости можно сделать map для константного поиска и разделить структуру на условия и поля
        aliasDictionary.add(new SqlAlias("идентификатор", String.format("%s.id", TABLE_ALIAS)));
        aliasDictionary.add(new SqlAlias("описание", String.format("%s.description", TABLE_ALIAS)));
        aliasDictionary.add(new SqlAlias("идентификатор статуса", String.format("%s.id_status", TABLE_ALIAS)));
        aliasDictionary.add(new SqlAlias("идентификатор категории", String.format("%s.id_category", TABLE_ALIAS)));
    }

    /**
     * Метод для создания фильтра с помощью пользовательского ввода.
     * @param name наименование фильтра
     * @param cond условие выборки из таблицы
     * @return созданный фильтр
     */
    public SimpleFilter createFilter(String name, String cond) {
        StringBuffer str = new StringBuffer(cond);
        int startIndex;
        int finishIndex;
        int startSearch = 0;
        for (SqlAlias alias: this.aliasDictionary) {
            if (alias.isLike() && str.indexOf(alias.getUserForm()) >= 0) {
                while (str.indexOf(alias.getUserForm(), startSearch) >= 0) {
                    startIndex = str.indexOf(alias.getUserForm(), startSearch) + alias.getUserForm().length();
                    finishIndex = str.indexOf(" ", startIndex);
                    if (finishIndex < 0) {
                        finishIndex = str.length();
                    }
                    startSearch = finishIndex;
                    String word = str.substring(startIndex, finishIndex);
                    str = str.replace(startIndex, finishIndex, String.format("%s%s%s", "%", word, "%"));
                }
            }
            startSearch = 0;
            while (str.indexOf(alias.getUserForm(), startSearch) >= 0) {
                startIndex = str.indexOf(alias.getUserForm());
                finishIndex = startIndex + alias.getUserForm().length();
                startSearch = finishIndex;
                str = str.replace(startIndex, finishIndex, alias.getSqlForm());
            }

        }
        return new SimpleFilter(name, String.format("SELECT * FROM request AS %s WHERE %s", TABLE_ALIAS, str));
    }

    /**
     * метод для добавления фильтра в коллекцию.
     * @param filter фильтр для добавления
     */
    public void addFilter(SimpleFilter filter) {
        this.filters.add(filter);
    }

    /**
     * Метод для получения фильтра по его имени.
     * @param name имя фильтра.
     * @return фильтр в случае успеха поиска, иначе null
     */
    public SimpleFilter getFilter(String name) {
        SimpleFilter result = null;
        for (SimpleFilter filter : filters) {
            if (name.equals(filter.getName())) {
                result = filter;
            }
        }
        return result;
    }
}
