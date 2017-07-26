package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс реализующий струткуру с неблокируемым кешем.
 * @author agavrikov
 * @since 26.07.2017
 * @version 1
 * @param <K> - Ключ
 * @param <V> - Значение
 */
public class NonBlockingStructure<K, V extends ConcurrentVersion> {

    /**
     * Поле для хранения структуры.
     */
    private ConcurrentHashMap<K, V> cMap = new ConcurrentHashMap<K, V>();

    /**
     * Метод для добавления элемента в структуру.
     * @param key ключ
     * @param value значение
     */
    public void add(K key, V value) {
        this.cMap.put(key, value);
    }

    /**
     * Метод для Обновления значения в структуре по ключу.
     * @param key ключ
     * @param value значение
     */
    public void update(K key, V value) {
        //O_o\\
        if (cMap.containsKey(key)) {
            int version = cMap.get(key).version;
            cMap.computeIfPresent(key, (k, v) -> {
                if (v.version != version) {
                    throw new OptimisticException("Error");
                } else {
                    value.version = v.version++;
                }
                return value;
            });
        }
    }

    /**
     * Метод для удалению по ключу.
     * @param key ключ
     */
    public void delete(K key) {
        cMap.remove(key);
    }


    public static void main (String[] args) {
        NonBlockingStructure<String, ConcurrentVersion<String>> struct = new NonBlockingStructure<String, ConcurrentVersion<String>>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    ConcurrentVersion<String> elem = new ConcurrentVersion<String>();
                    elem.value = String.format("Test %s val", i);
                    struct.add(String.format("Test %s key", i), elem);
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 100; i < 200; i++) {
                    ConcurrentVersion<String> elem = new ConcurrentVersion<String>();
                    elem.value = String.format("Test %s val", i);
                    struct.update(String.format("Test %s key", i), elem);
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    ConcurrentVersion<String> elem = new ConcurrentVersion<String>();
                    elem.value = String.format("Test %s val new", i);
                    struct.update(String.format("Test %s key", i), elem);
                }
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    ConcurrentVersion<String> elem = new ConcurrentVersion<String>();
                    elem.value = String.format("Test %s val new", i);
                    struct.add(String.format("Test %s key", i), elem);
                }
            }
        });

        thread.start();
        thread1.start();
        thread3.start();
        thread4.start();
    }
}
