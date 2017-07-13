package ru.job4j;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс для сортировки пользователей.
 * @author agavrikov
 * @since 13.07.2017
 * @version 1
 */
public class SortUser {

    /**
     * метод для сортировки пользователей.
     * @param list список пользователей
     * @return TreeSet с сортировкой по возрасту
     */
    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<User>();
        for (User user : list) {
            result.add(user);
        }
        return result;
    }
}
