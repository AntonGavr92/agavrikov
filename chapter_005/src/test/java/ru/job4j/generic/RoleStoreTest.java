package ru.job4j.generic;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test RoleStore class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class RoleStoreTest {

    /**
     * Метод для тестирования добавления.
     */
    @Test
    public void testAddUser() {
        RoleStore store = new RoleStore(10);
        User user = new User();
        user.setId("122121");
        store.add(user);
        String result = store.getArray().get(user).getId();
        String expected = "122121";
        assertThat(result, is(expected));
    }

    /**
     * Метод для тестирования изменения.
     */
    @Test
    public void testUpdateUser() {
        RoleStore store = new RoleStore(10);
        User user = new User();
        user.setId("122121");
        store.add(user);

        User user2 = new User();
        user2.setId("11");

        store.update(user, user2);
        String result = store.getArray().get(user2).getId();
        String expected = "11";

        assertThat(result, is(expected));
    }

    /**
     * Метод для тестирования удаления.
     */
    @Test
    public void testDeleteUser() {
        RoleStore store = new RoleStore(10);
        User user = new User();
        user.setId("122121");
        store.add(user);
        store.delete(user);
        Base result = store.getArray().get(user);
        Base expected = null;
        assertThat(result, is(expected));
    }

}