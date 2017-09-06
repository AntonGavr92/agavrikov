package ru.job4j;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * FileCacheManager class.
 * @author agavrikov
 * @since 28.08.2017
 * @version 1
 */
public class FileCacheManager extends CacheManger<String, String> {


    /**
     * Cache map.
     */
    private final HashMap<String, SoftReference<String>> CacheMap = new HashMap<String, SoftReference<String>>();

    /**
     * Method for adding key/value in map.
     * @param key key
     * @param value value
     */
    @Override
    public void add(String key, String value) {
        SoftReference<String> valMap = new SoftReference<>(value);
        this.CacheMap.put(key, valMap);
    }

    /**
     * Method for get value by key from map.
     * @param key key
     * @return file content
     */
    @Override
    public String get(String key) {
        String result = "";
        if (this.CacheMap.containsKey(key)) {
            result = this.CacheMap.get(key).get();
        } else {
            result = createCache(key);
        }
        return result;
    }


    /**
     * Method for create cache file.
     * @param key key in map
     * @return file content
     */
    private String createCache(String key) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(new File(key)))) {
            String line = bf.readLine();
            while (line != null) {
                sb.append(line);
                line = bf.readLine();
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        this.add(key, sb.toString());
        return sb.toString();
    }

    /**
     * Point of start program.
     * @param args params
     */
    public static void main(String[] args) {
        FileCacheManager fcm = new FileCacheManager();
        fcm.get(fcm.getClass().getClassLoader().getResource("logjmap.txt").getPath());
        fcm.get(fcm.getClass().getClassLoader().getResource("logjstack.txt").getPath());
        fcm.get(fcm.getClass().getClassLoader().getResource("logjmap.txt").getPath());
    }
}
