package ru.job4j.profession;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Enginer class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class EnginerTest {

    /**
     * Test method createChart.
     */
    @Test
    public void whenRealizeChartThenChartChartNameIsRealized() {
        Enginer enginer = new Enginer(30, 8, "LADA");
        Chart chart = enginer.createChart("Technical chart 334", "Technical chart 334 description");
        String result = enginer.realizeChart(chart);
        String expected = "Chart Technical chart 334 is realized";
        assertThat(result, is(expected));
    }

    /**
     * Test get name from Profession class.
     */
    @Test
    public void getName() {
        Enginer enginer = new Enginer(30, 8, "LADA");
        String result = enginer.getName();
        String expected = "Enginer";
        assertThat(result, is(expected));
    }

    /**
     * Test createChart method.
     */
    @Test
    public void createChart() {
        Enginer enginer = new Enginer(30, 8, "LADA");
        String result = enginer.createChart("Technical chart 334", "Technical chart 334 description").getName();
        String expected = "Technical chart 334";
        assertThat(result, is(expected));
    }

}