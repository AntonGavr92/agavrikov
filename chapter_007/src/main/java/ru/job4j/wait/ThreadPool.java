package ru.job4j.wait;

import java.util.LinkedList;
import java.util.Queue;

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
     * Очередь для выполнения задач, за которой будет мониторить пул.
     */
  private final Queue<Work> queue = new LinkedList<>();
    /**
     * Метод, для добавления работы на потоке.
     * @param work некая работа
     * @throws InterruptedException исключение
     */
    public void add(Work work) throws InterruptedException {
        synchronized (queue) {
            queue.add(work);
            queue.notifyAll();
        }
    }

    public void createPool() {
        for (int i = 0; i < countThreads; i++) {
            Thread thread = new Thread( new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        synchronized (queue) {
                            try {
                                while (queue.size() == 0) {
                                    queue.wait();
                                }
                                queue.poll().isDone = true;

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }
}
