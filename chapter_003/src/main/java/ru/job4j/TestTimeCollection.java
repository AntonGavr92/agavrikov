package ru.job4j;

import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.Iterator;

/**
 * Классдля тестирования коллекций.
 * @author agavrikov
 * @since 13.07.2017
 * @version 1
 */
public class TestTimeCollection {

    /**
     * поле для хранения объекта Random.
     */
    private Random rnd = new Random();

    /**
     * Метод для добавления определенного количества элементов в коллекецию, возвращающий затраченное время.
     * @param collection - Коллекция, в которую необходимо произвести добавление
     * @param amount - Количество элементов для добавления
     * @return время в миллисекундах затраченное на операцию
     */
    public long add(Collection<String> collection, int amount) {
        for (int i = 0; i < amount; i++) {
            collection.add(this.getRandomString(10, "abcdefgklmnoprstyqzxn1234"));
        }
        return new Date().getTime();
    }

    /**
     * Метод для удаления определенного количества элементов из коллекции, возвращающий затраченное время.
     * @param collection - Коллекция, в которую необходимо произвести добавление
     * @param amount - Количество элементов для добавления
     * @return время в миллисекундах затраченное на операцию
     */
    public long delete(Collection<String> collection, int amount) {
        int counter = 0;
        Iterator<String> iter = collection.iterator();
        while (iter.hasNext()) {
            String s = iter.next();
            iter.remove();
            if (counter > amount) {
                break;
            }

        }
        return new Date().getTime();
    }

    /**
     * Метод для получения рандомной строки.
     * @param length длинна строки
     * @param alphabet алфавит
     * @return случайную строку
     */
    public String getRandomString(int length, String alphabet) {
        StringBuilder sb = new StringBuilder(Math.max(length, 16));
        for (int i = 0; i < length; i++) {
            int len = alphabet.length();
            int random = rnd.nextInt(len);
            char c = alphabet.charAt(random);
            sb.append(c);
        }
        return sb.toString();
    }
}
