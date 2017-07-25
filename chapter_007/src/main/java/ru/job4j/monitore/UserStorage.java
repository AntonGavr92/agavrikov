package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;

/**
 * Класс реализующий потокобезопасную структуру данных для хранения пользователей.
 * @author agavrikov
 * @since 25.07.2017
 * @version 1
 */
@ThreadSafe
public class UserStorage {

    /**
     * Струкетура для хранения пользователей.
     */
    @GuardedBy("this")
    private HashMap<Integer, User> storage = new HashMap<Integer, User>();

    /**
     * Метод для добавления пользователей в структуру.
     * @param user пользователь
     */
    public void add(User user) {
        storage.put(user.getId(), user);
    }

    /**
     * Метод для удаления пользователя из структуры.
     * @param user пользователь
     */
    public void delete(User user) {
        if (storage.containsKey(user.getId())) {
            storage.remove(user.getId());
        }
    }

    /**
     * Метод для обновления пользователя.
     * @param userForUpdate пользователь, которого необходимо обновить.
     * @param user - пользователь, на которого обновляем
     */
    public void update(User userForUpdate, User user) {
        storage.put(userForUpdate.getId(), user);
    }

    /**
     * Метод для перевода средств одного пользователя другому.
     * @param fromId идентификатор, с которого нужно переводить.
     * @param toId идентификатор, на который нужно переводить
     * @param amount количество средств.
     */
    public void transfer(int fromId, int toId, int amount) {
        if (storage.containsKey(fromId) && storage.containsKey(toId)) {
            User userFrom = storage.get(fromId);
            User userTo = storage.get(toId);
            if (userFrom.getAmount() >= amount) {
                userFrom.setAmount(userFrom.getAmount() - amount);
                userTo.setAmount(userTo.getAmount() + amount);
            }
        }
    }

}
