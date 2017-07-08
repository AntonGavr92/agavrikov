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
        Human human = new Human("John", 10, "male");
        String result = teacher.teach(human);
        String expected = "Schoolboy John trained.";
        assertThat(result, is(expected));
    }

}