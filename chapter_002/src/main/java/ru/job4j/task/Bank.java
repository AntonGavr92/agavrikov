package ru.job4j.task;


import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Class описывающий посещения банка.
 * @author agavrikov
 * @since 17.07.2017
 * @version 1
 */
public class Bank {

    /**
     * Поле для хранения событий входа и выхода. long - время события, boolean - флаг о входе(если это вход - true, иначе false).
     */
    private Set<Event> events = new TreeSet<Event>();

    /**
     * Метод, фиксирующий посетителя банка.
     * @param customer - посетитель
     */
    public void traceCustomer(Customer customer) {
        this.events.add(new Event(customer.getTimeEnter(), true));
        this.events.add(new Event(customer.getTimeExit(), false));
    }

    /**
     * Метод для получения отрезка времени с максимальным количеством посетителей.
     * @return массив, хранящий границы отрезка времени с максимальным количеством посещений
     */
    public long[] getTimeMaxCustomersInBank() {
        int maxCustomers = 0;
        int customersRealTime = 0;
        long timeStart = 0;
        long timeExit = 0;
        for (Event event : this.events) {
            if (event.isEnter()) {
                customersRealTime++;
            } else {
                customersRealTime--;
            }

            if (maxCustomers < customersRealTime) {
                maxCustomers = customersRealTime;
                timeStart = event.getTime();
                timeExit = 0;
            } else if (timeExit == 0 && maxCustomers > customersRealTime) {
                timeExit = event.getTime();
            }
        }
        return new long[]{timeStart, timeExit};
    }
}
