package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Test UserTest class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class UserTest {

    /**
     * Тестирование map с помещенными в нее Users без переопределения equals и hasCode.
     */
    @Test
    public void testWithoutOverrideEqualsAndHashCode() {
        User user1 = new User("Ivan", 0, new Calendar("20 May 1992"));
        User user2 = new User("Ivan", 0, new Calendar("20 May 1992"));

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(map);
    }

}