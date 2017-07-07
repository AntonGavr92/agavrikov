package ru.job4j.profession;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Profession class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class ProfessionTest {

    /**
     * Test getter getName.
     */
    @Test
    public void getName() {
        Profession prof = new Profession("Enginer");
        String result = prof.getName();
        String expected = "Enginer";
        assertThat(result, is(expected));
    }
}