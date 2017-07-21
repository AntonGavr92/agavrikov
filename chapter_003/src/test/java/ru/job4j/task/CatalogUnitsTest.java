package ru.job4j.task;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test CatalogUnits class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class CatalogUnitsTest {
    /**
     * Тестирование сортировки по возрастанию.
     */
    @Test
    public void getCatalogAsc() {
        CatalogUnits catalog = new CatalogUnits();
        catalog.addUnit("K1\\SK1");
        catalog.addUnit("K1\\SK2");
        catalog.addUnit("K1\\SK1\\SSK1");
        catalog.addUnit("K1\\SK2\\SSK1");
        catalog.addUnit("K1\\SK1\\SSK2");
        catalog.addUnit("K2");
        catalog.addUnit("K2\\SK1");
        catalog.addUnit("K2\\SK1\\SSK1");
        catalog.addUnit("K2\\SK1\\SSK2");
        String result = "";
        for (String s : catalog.getCatalogAsc()) {
            System.out.println(s);
            result = s;
        }
        String expected = "K2\\SK1\\SSK2";
        assertThat(result, is(expected));
    }

    /**
     * Тестирование сортировки по убыванию.
     */
    @Test
    public void getCatalogDesc() {
        CatalogUnits catalog = new CatalogUnits();
        catalog.addUnit("K1\\SK1");
        catalog.addUnit("K1\\SK2");
        catalog.addUnit("K1\\SK1\\SSK1");
        catalog.addUnit("K1\\SK2\\SSK1");
        catalog.addUnit("K1\\SK1\\SSK2");
        catalog.addUnit("K2");
        catalog.addUnit("K2\\SK1");
        catalog.addUnit("K2\\SK1\\SSK1");
        catalog.addUnit("K2\\SK1\\SSK2");
        String result = "";
        for (String s : catalog.getCatalogDesc()) {
            System.out.println(s);
            result = s;
        }
        String expected = "K1\\SK2\\SSK1";
        assertThat(result, is(expected));
    }

}