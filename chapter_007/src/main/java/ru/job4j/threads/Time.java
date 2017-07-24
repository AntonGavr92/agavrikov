package ru.job4j.threads;

import java.util.Date;

/**
 * Класс реализующий контроль времени за другим потоком.
 * @author agavrikov
 * @since 24.07.2017
 * @version 1
 */
public class Time implements Runnable {

    /**
     * Точка входа.
     * @param arg параметры.
     */
    public static void main(String[] arg) {
        Thread thread = new Thread(new Time());
        thread.start();
    }

    /**
     * Метод для запуска дополнительного потока и отслеживанием затраченного времени на выполнение. При выход за временную границу, метод устанавливает у своего потока interrupt.
     */
    @Override
    public void run() {
        long timeStart = new Date().getTime();
        Thread countChar = new Thread(new CountChar());
        countChar.start();
        while (!Thread.currentThread().isInterrupted()) {
            if (new Date().getTime() - timeStart > 1) {
                countChar.interrupt();
                Thread.currentThread().interrupt();
            }
        }
    }
}
