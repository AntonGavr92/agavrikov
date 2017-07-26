package ru.job4j.wait;

/**
 * Класс реализующий шаблон Producer Customer.
 * @author agavrikov
 * @since 26.07.2017
 * @version 1
 */
public class ThreadPool {

    /**
     * Поле для хранения количества процессоров.
     */
    private final int countThreads = Runtime.getRuntime().availableProcessors();

    /**
     * Поле для хранения работающих потоков.
     */
    private int countWorkingThreads = 0;

    /**
     * Метод, для добавления работы на потоке.
     * @param work некая работа
     * @throws InterruptedException исключение
     */
    public void add(Work work) throws InterruptedException {
        synchronized (this) {
            while (countWorkingThreads >= countThreads) {
                this.wait();
            }
            countWorkingThreads++;
            //work.isDone = true;
            //countWorkingThreads--;
        }
    }

    /**
     * Метод для проверки свободных потоков, и при их наличии активизирующий ждущие потоки.
     */
    public void checkCountThreads() {
        synchronized (this) {
            if (countWorkingThreads < countThreads) {
                this.notify();
            }
        }
    }
}
