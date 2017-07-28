package ru.job4j.filters;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class Filters.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class FiltersTest {

    /**
     * Метод для тестирования добавления и получения фильтра по имени.
     */
    @Test
    public void whenCreateNewFilterWithNameAndFindItByNameThenSameFilter() {
        Filters filters = new Filters();
        SimpleFilter filter = filters.createFilter("Test", "описание должно содержать строку test или описание должно содержать строку tost и идентификатор больше 3");
        filters.addFilter(filter);
        SimpleFilter expected = filter;
        assertThat(filter, is(expected));
    }

    /**
     * Метод для тестирования добавления и получения фильтра по имени и проверки его запроса.
     */
    @Test
    public void whenCreateNewFilterWithNameAndFindItByNameThenSameFilterWithSqlQuery() {
        Filters filters = new Filters();

        filters.addFilter(filters.createFilter("Test", "описание должно содержать строку test или описание должно содержать строку tost и идентификатор больше 3 и описание должно содержать строку t"));
        SimpleFilter filter = filters.getFilter("Test");
        String result = filter.getQuery();
        String expected = "SELECT * FROM request AS i WHERE i.description LIKE %test% OR i.description LIKE %tost% AND i.id > 3 AND i.description LIKE %t%";
        assertThat(result, is(expected));
    }

}