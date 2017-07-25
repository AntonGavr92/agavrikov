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
     * Метод для получения счетчика.
     * @return текущее значение счетчика.
     */
    public int incremant() {
        return counter;
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
                while (count.incremant() < 1000) {
                    count.counter++;
                }
                System.out.println(String.format("1 thread %s", count.counter));
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count.incremant() < 1000) {
                    count.counter++;
                }
                System.out.println(String.format("2 thread %s", count.counter));
            }
        });
        thread.start();
        thread2.start();
    }
}
