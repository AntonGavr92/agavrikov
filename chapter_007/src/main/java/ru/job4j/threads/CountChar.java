package ru.job4j.threads;

/**
 * Класс реализующий счетчик символов в строке.
 * @author agavrikov
 * @since 24.07.2017
 * @version 1
 */
public class CountChar implements Runnable {
    /**
     * Метод для подсчета символов в строке.
     */
    @Override
    public void run() {
        String text = "Computer users take it for granted that their systems can do more than one thing at a time Computer users take it for granted that their systems can do more than one thing at a time Computer users take it for granted that their systems can do more than one thing at a time Computer users take it for granted that their systems can do more than one thing at a time Computer users take it for granted that their systems can do more than one thing at a time Computer users take it for granted that their systems can do more than one thing at a time";
        int counter = 0;
        char[] res = text.toCharArray();
        for (char symbol : res) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            counter++;
        }
        if (!Thread.currentThread().isInterrupted()) {
            System.out.println("Text has " + counter + " spaces.");
        }
    }
}
