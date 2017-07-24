package ru.job4j.threads;

import java.util.Date;

/**
 * Класс реализующий паралельный счетчик пробелов и слов в строке.
 * @author agavrikov
 * @since 24.07.2017
 * @version 1
 */
public class SymbolsCounter {

    /**
     * Метода реализующий запуск двух потоков и параллельного выполнения.
     * @param args параметры.
     */
    public static void main(String[] args) {
        System.out.println("Start program");
        String text = "Computer users take it for granted that their systems can do more than one thing at a time Computer users take it for granted that their systems can do more than one thing at a time Computer users take it for granted that their systems can do more than one thing at a time Computer users take it for granted that their systems can do more than one thing at a time Computer users take it for granted that their systems can do more than one thing at a time Computer users take it for granted that their systems can do more than one thing at a time";
        Thread threadSpace = new Thread(new Runnable() {
            @Override
            public void run() {
                long timeStart = new Date().getTime();
                int counter = 0;
                char[] res = text.toCharArray();
                for (char symbol : res) {
                    if (new Date().getTime() - timeStart > 999) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    if (Character.toString(symbol).equals(" ")) {
                        counter++;
                    }
                }
                if (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Text has " + counter + " spaces.");
                }
            }
        });

        Thread threadWords = new Thread(new Runnable() {
            @Override
            public void run() {
                long timeStart = new Date().getTime();
                String[] res = text.split(" ");
                if (new Date().getTime() - timeStart > 999) {
                    Thread.currentThread().interrupt();
                }
                if(!Thread.currentThread().isInterrupted()) {
                    System.out.println("Text has " + res.length + " words.");
                }
            }
        });
        threadSpace.start();
        threadWords.start();
        try {
            threadSpace.join();
            threadWords.join();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End program");
    }
}
