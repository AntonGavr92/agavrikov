package ru.job4j.task;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


/**
 * Class тестирующий класс Bank.
 * @author agavrikov
 * @since 18.07.2017
 * @version 1
 */
public class BankTest {

    /**
     * Тестирование основное.
     * 1500279934 - Mon, 17 Jul 2017 08:25:34 GMT.
     * 1500283534 - Mon, 17 Jul 2017 09:25:34 GMT. +
     * 1500279454 - Mon, 17 Jul 2017 08:17:34 GMT.
     * 1500287134 - Mon, 17 Jul 2017 10:25:34 GMT.
     * 1500282934 - Mon, 17 Jul 2017 09:15:34 GMT. +
     * 1500300934 - Mon, 17 Jul 2017 14:15:34 GMT.
     */
    @Test
    public void mainTest() {
        Customer customer = new Customer(1500279934, 1500283534);
        Customer customer2 = new Customer(1500279454, 1500287134);
        Customer customer3 = new Customer(1500282934, 1500283534);
        Bank bank = new Bank();
        bank.traceCustomer(customer);
        bank.traceCustomer(customer2);
        bank.traceCustomer(customer3);
        long result = bank.getTimeMaxCustomersInBank().total();
        long expected = 600;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование с одинаковыми точками входа.
     * 1500279934 - Mon, 17 Jul 2017 08:25:34 GMT.
     * 1500283534 - Mon, 17 Jul 2017 09:25:34 GMT.
     * 1500279454 - Mon, 17 Jul 2017 08:17:34 GMT.
     * 1500287134 - Mon, 17 Jul 2017 10:25:34 GMT.
     * 1500282934 - Mon, 17 Jul 2017 09:15:34 GMT.
     * 1500300934 - Mon, 17 Jul 2017 14:15:34 GMT.
     *
     * 1500287134 - Mon, 17 Jul 2017 10:25:34 GMT.
     * 1500300934 - Mon, 17 Jul 2017 14:15:34 GMT.
     *
     * 1500287134 - Mon, 17 Jul 2017 10:25:34 GMT.
     * 1500300934 - Mon, 17 Jul 2017 14:15:34 GMT.
     *
     * 1500287134 - Mon, 17 Jul 2017 10:25:34 GMT.
     * 1500300934 - Mon, 17 Jul 2017 14:15:34 GMT.
     *
     * 1500287134 - Mon, 17 Jul 2017 10:25:34 GMT. +
     * 1500300934 - Mon, 17 Jul 2017 14:15:34 GMT. +
     */
    @Test
    public void testSame() {
        Customer customer = new Customer(1500279934, 1500283534);
        Customer customer2 = new Customer(1500279454, 1500287134);
        Customer customer3 = new Customer(1500282934, 1500283534);

        Customer customer4 = new Customer(1500287134, 1500300934);
        Customer customer5 = new Customer(1500287134, 1500300934);
        Customer customer6 = new Customer(1500287134, 1500300934);
        Customer customer7 = new Customer(1500287134, 1500300934);

        Bank bank = new Bank();
        bank.traceCustomer(customer);
        bank.traceCustomer(customer2);
        bank.traceCustomer(customer3);
        bank.traceCustomer(customer4);
        bank.traceCustomer(customer5);
        bank.traceCustomer(customer6);
        bank.traceCustomer(customer7);
        long result = bank.getTimeMaxCustomersInBank().total();
        long expected = 13800;
        assertThat(result, is(expected));
    }

}