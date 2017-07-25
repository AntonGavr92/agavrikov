package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Класс реализующий потокобезопасный счетчик.
 * @author agavrikov
 * @since 24.07.2017
 * @version 1
 */
@ThreadSafe
public class Count {

    /**
     * Поле для хранения счетчика.
     */
    @GuardedBy("this")
    int counter = 0;

    /**
     * Метод для инкремента счетчика.
     * @return текущее значение счетчика.
     */
    public void incremant() {
        synchronized (this) {
            this.counter++;
        }
    }

    /**
     * Точка входа.
     * @param args параметры.
     */
    public static void main(String[] args) {
        Count count = new Count();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    count.incremant();
                }
                System.out.println(String.format("1 thread %s", count.counter));
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    count.incremant();
                }
                System.out.println(String.format("2 thread %s", count.counter));
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    count.incremant();
                }
                System.out.println(String.format("3 thread %s", count.counter));
            }
        });
        thread.start();
        thread2.start();
        thread3.start();
        try {
            thread.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("additional %s", count.counter));
    }
}
