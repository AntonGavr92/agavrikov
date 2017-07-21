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
        Role role = new Role();
        role.setId("122121");
        store.add(role);
        String result = store.getArray().get(role).getId();
        String expected = "122121";
        assertThat(result, is(expected));
    }

    /**
     * Метод для тестирования изменения.
     */
    @Test
    public void testUpdateUser() {
        RoleStore store = new RoleStore(10);
        Role role = new Role();
        role.setId("122121");
        store.add(role);

        Role role2 = new Role();
        role2.setId("11");

        store.update(role, role2);
        String result = store.getArray().get(role2).getId();
        String expected = "11";

        assertThat(result, is(expected));
    }

    /**
     * Метод для тестирования удаления.
     */
    @Test
    public void testDeleteUser() {
        RoleStore store = new RoleStore(10);
        Role role = new Role();
        role.setId("122121");
        store.add(role);
        store.delete(role);
        Base result = store.getArray().get(role);
        Base expected = null;
        assertThat(result, is(expected));
    }

}