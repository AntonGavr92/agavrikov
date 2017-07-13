package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования SortUser.
 * @author agavrikov
 * @since 13.07.2017
 * @version 1
 */
public class SortUserTest {
    /**
     * Метод для тестирования сортировки.
     */
    @Test
    public void sort() {
        List<User> list = new ArrayList<User>();
        list.add(new User("Anton", 25));
        list.add(new User("Ivan", 18));
        list.add(new User("Aleksey", 30));
        SortUser sortUser = new SortUser();
        Set<User> sortSet = sortUser.sort(list);

        for (User user : sortSet) {
            String result = user.getName();
            String expected = "Ivan";
            assertThat(result, is(expected));
            break;
        }
    }

    /**
     * Метод для тестирования сортировки по длинне имени.
     */
    @Test
    public void sortNameLength() {
        List<User> list = new ArrayList<User>();
        list.add(new User("Anton", 25));
        list.add(new User("Ivan", 18));
        list.add(new User("Aleksey", 30));
        SortUser sortUser = new SortUser();
        List<User> sortlist = sortUser.sortNameLength(list);

        int result = sortlist.get(2).getName().length();
        int expected = 7;
        assertThat(result, is(expected));
    }

    /**
     * Метод для тестирования сортировки по двум полям.
     */
    @Test
    public void whenSortListThen3ElementWithAge24() {
        List<User> list = new ArrayList<User>();
        list.add(new User("Ivan", 30));
        list.add(new User("Anton", 25));
        list.add(new User("Ivan", 24));
        list.add(new User("Anton", 18));

        SortUser sortUser = new SortUser();
        List<User> sortList = sortUser.sortByAllFields(list);

        int result = sortList.get(2).getAge();
        int expected = 24;
        assertThat(result, is(expected));

    }

    /**
     * Метод для тестирования сортировки по двум полям.
     */
    @Test
    public void sortByAllField1s() {
        List<User> list = new ArrayList<User>();
        list.add(new User("Ivan", 30));
        list.add(new User("Anton", 25));
        list.add(new User("Ivan", 24));
        list.add(new User("Anton", 18));

        SortUser sortUser = new SortUser();
        List<User> sortList = sortUser.sortByAllFields(list);

        String result = sortList.get(0).getName();
        String expected = "Anton";
        assertThat(result, is(expected));

    }
}