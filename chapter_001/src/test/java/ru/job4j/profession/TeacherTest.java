package ru.job4j.profession;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Teacher class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class TeacherTest {

    /**
     * Test get name from Profession class.
     */
    @Test
    public void getName() {
        Teacher teacher = new Teacher(10, true, "biology");
        String result = teacher.getName();
        String expected = "Teacher";
        assertThat(result, is(expected));
    }

    /**
     * Test teach method.
     */
    @Test
    public void teach() {
        Teacher teacher = new Teacher(10, true, "biology");
        String result = teacher.teach(12);
        String expected = "Schoolboy with id " + 12 + " trained.";
        assertThat(result, is(expected));
    }

}