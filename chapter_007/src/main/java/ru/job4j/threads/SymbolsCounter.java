package ru.job4j.threads;

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
        String text = "Computer users take it for granted that their systems can do more than one thing at a time";
        Thread threadSpace = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                char[] res = text.toCharArray();
                for (char symbol : res) {
                    if (Character.toString(symbol).equals(" ")) {
                        counter++;
                    }
                }
                System.out.println("Text has " + counter + " spaces.");
            }
        });

        Thread threadWords = new Thread(new Runnable() {
            @Override
            public void run() {
                String[] res = text.split(" ");
                System.out.println("Text has " + res.length + " words.");
            }
        });

        threadSpace.start();
        threadWords.start();
    }
}
