package ru.job4j.task;

/**
 * Class описывающий промежуток времени с максимальным количеством посетителей.
 * @author agavrikov
 * @since 17.07.2017
 * @version 1
 */
public class Pick {

    /**
     * Начало временного отрезка с максимальным количеством посетителей.
     */
    private long start;

    /**
     * Конец временного отрезка с максимальным количеством посетителей.
     */
    private long stop;

    /**
     * Конструктор.
     * @param start начало временного отрезка с максимальным количеством посетителей.
     * @param stop конец временного отрезка с максимальным количеством посетителей.
     */
    public Pick(long start, long stop) {
        this.start = start;
        this.stop = stop;
    }

    /**
     * Метод для получения длительности отрезка времени с максимальным количеством клиентов.
     * @return время в миллисекундах
     */
    public long total() {
        return this.stop - this.start;
    }

    /**
     * Геттер.
     * @return начало временного отрезка с максимальным количеством посетителей.
     */
    public long getStart() {
        return this.start;
    }


    /**
     * Геттер.
     * @return конец временного отрезка с максимальным количеством посетителей.
     */
    public long getStop() {
        return this.stop;
    }
}
