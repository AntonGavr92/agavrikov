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
        Human human = new Human("John", 20, "male");
        String result = doctor.heal(human);
        String expected = "Patient John healthy.";
        assertThat(result, is(expected));
    }

}