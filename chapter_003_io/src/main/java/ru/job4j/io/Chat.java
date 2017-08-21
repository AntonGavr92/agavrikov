package ru.job4j.io;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Random;

/**
 * Класс, имитирующий работу чата со спец командами и логированием в рамках работы программы.
 * @author agavrikov
 * @since 17.08.2017
 * @version 1
 */
public class Chat {

    /**
     * Команда чата, для приостановки работы чата.
     */
    private static final String STOP = "стоп";

    /**
     * Команда чата, для продолжения работы чата.
     */
    private static final String CONTINUE = "продолжить";

    /**
     * Команда чата, для завершения работы чата.
     */
    private static final String FINISH = "закончить";


    /**
     * Точка входа в программу.
     * @param args входящие параметры
     */
    public static void main(String[] args) {
        Chat chat = new Chat();
        chat.start();
    }

    /**
     * Метод, реализующий работу чата.
     */
    public void start() {
        LinkedList<String> phrases = new LinkedList<String>();
        ClassLoader classLoader = Chat.class.getClassLoader();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader brPhrases = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("phrases.txt")));
             FileOutputStream fos = new FileOutputStream(classLoader.getResource("logs.txt").getFile())) {
            String phrase = brPhrases.readLine();
            while (phrase != null) {
                phrases.add(phrase);
                phrase = brPhrases.readLine();
            }
            Random rand = new Random();
            boolean stoped = false;
            String text = br.readLine();
            while (!FINISH.equals(text)) {
                if (STOP.equals(text)) {
                    fos.write(String.format("%s пользователь ввел стоп.%s", System.currentTimeMillis(), System.lineSeparator()).getBytes());
                    stoped = true;
                } else if (stoped && CONTINUE.equals(text)) {
                    fos.write(String.format("%s пользователь ввел продолжить.%s", System.currentTimeMillis(), System.lineSeparator()).getBytes());
                    stoped = false;
                }
                if (!stoped) {
                    System.out.println(phrases.get(rand.nextInt(phrases.size())));
                }
                text = br.readLine();
            }
            fos.write(String.format("%s пользователь закончил работу.%s", System.currentTimeMillis(), System.lineSeparator()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
