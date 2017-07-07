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
        String result = enginer.createChart();
        String expected = "Chart ready";
        assertThat(result, is(expected));
    }

}