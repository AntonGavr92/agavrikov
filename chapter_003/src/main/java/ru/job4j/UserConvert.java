package ru.job4j;

import java.util.HashMap;
import java.util.List;

/**
 * Класс для конвертации списка пользователей в map.
 * @author agavrikov
 * @since 13.07.2017
 * @version 1
 */
public class UserConvert {

    /**
     * Метод для конвертирования списка пользователей в map.
     * @param list - список пользователей
     * @return карта пользователей
     */
    HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<Integer, User>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
