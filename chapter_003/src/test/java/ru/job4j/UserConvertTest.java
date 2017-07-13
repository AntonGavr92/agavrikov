package ru.job4j;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class UserConvert.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class UserConvertTest {
    /**
     * Тестирование метода process.
     */
    @Test
    public void whenUserListSize2ThenResultMapSizeSame() {
        List<User> usersList = new LinkedList<User>();
        usersList.add(new User(0, "Anton", "Yekaterinburg"));
        usersList.add(new User(1, "Ivan", "Yekaterinburg"));

        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> resultMap =  userConvert.process(usersList);

        int result = resultMap.size();
        int expected = 2;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода process.
     */
    @Test
    public void whenUserWithId1AndNameIvanInListThanUserWithSameIdAndSameNameExistInMap() {
        List<User> usersList = new LinkedList<User>();
        usersList.add(new User(0, "Anton", "Yekaterinburg"));
        usersList.add(new User(1, "Ivan", "Yekaterinburg"));

        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> resultMap =  userConvert.process(usersList);

        String result = resultMap.get(1).getName();
        String expected = "Ivan";
        assertThat(result, is(expected));
    }

}