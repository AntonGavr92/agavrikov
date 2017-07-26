package ru.job4j.wait;

/**
 * Класс описывающий механизм блокировок Lock.
 * @author agavrikov
 * @since 26.07.2017
 * @version 1
 */
public class SimpleLock {

    /**
     * Поле в котором хранится флаг блокировки.
     */
    private boolean locked = false;

    /**
     * Метод для установки флага locked в true.
     * @throws InterruptedException исключение
     */
    public synchronized void lock() throws InterruptedException {
        while(this.locked) {
            this.wait();
        }

        this.locked = true;
    }

    /**
     * Метод для установки флага locked в false и последующей активизацией потоков.
     * @throws InterruptedException исключение
     */
    public synchronized void unlock() {
        this.locked = false;
        this.notify();
    }
}
