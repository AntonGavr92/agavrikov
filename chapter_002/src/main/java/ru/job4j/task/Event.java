package ru.job4j.task;

/**
 * Class описывающий событие.
 * @author agavrikov
 * @since 17.07.2017
 * @version 1
 */
public class Event implements Comparable<Event>{

    /**
     * Время события.
     */
    private long time;

    /**
     * событие характеризует вход.
     */
    private boolean isEnter;

    /**
     * Конструктор для инициализации.
     * @param isEnter событие характеризует вход.
     * @param time время события
     */
    public Event(long time, boolean isEnter) {
        this.isEnter = isEnter;
        this.time = time;
    }

    /**
     * Геттер времени.
     * @return время события
     */
    public long getTime(){
        return this.time;
    }

    /**
     * Геттер характеристики события.
     * @return события является входом
     */
    public boolean isEnter() {
        return this.isEnter;
    }

    @Override
    public int compareTo(Event o) {
        return this.time < o.getTime() ? -1 : 1;
    }
}
