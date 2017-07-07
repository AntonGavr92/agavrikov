package ru.job4j.profession;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Doctor class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class DoctorTest {

    /**
     * Test get name from Profession class.
     */
    @Test
    public void getName() {
        Doctor doctor = new Doctor(32, true, "therapist");
        String result = doctor.getName();
        String expected = "Doctor";
        assertThat(result, is(expected));
    }

    /**
     * Test heal method.
     */
    @Test
    public void heal() {
        Doctor doctor = new Doctor(32, true, "therapist");
        String result = doctor.heal(1);
        String expected = "Patient with id " + 1 + " healthy.";
        assertThat(result, is(expected));
    }

}