package ru.job4j;

import java.util.*;

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

    /**
     * Сортировка списка по длинне имени.
     * @param list список, который необходимо отсортировать
     * @return отсортированный список
     */
    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getName().length() > o2.getName().length()) {
                    return 1;
                } else if (o1.getName().length() < o2.getName().length()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return list;
    }

    /**
     * Сортировка списка имени и возрасту.
     * @param list список, который необходимо отсортировать
     * @return отсортированный список
     */
    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getName().compareTo(o2.getName()) > 0) {
                    return 1;
                } else if (o1.getName().compareTo(o2.getName()) < 0) {
                    return -1;
                } else {
                    if (o1.getAge() > o2.getAge()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });
        return list;
    }
}
