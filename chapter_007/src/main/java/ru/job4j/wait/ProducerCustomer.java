package ru.job4j.wait;

/**
 * Класс реализующий шаблон Producer Customer.
 * @author agavrikov
 * @since 24.07.2017
 * @version 1
 */
public class ProducerCustomer {

    /**
     * Поле для хранения монитора.
     */
    private final Object lock = new Object();

    /**
     * Поле для хранения флага об измененных данных.
     */
    private boolean blockCustomer = true;

    /**
     * Метод, производящий действие в зависимости от producer.
     * @throws InterruptedException исключение
     */
    public void doSomething() throws InterruptedException {
        synchronized (this.lock) {
            while (this.blockCustomer) {
                System.out.println("wait");
                lock.wait();
            }
            System.out.println("do");
        }
    }

    /**
     * Метод реализующий работу producer, изменяющий флаг blockCustomer.
     * @param enable true или false
     */
    public void changeBlock(boolean enable) {
        synchronized (this.lock) {
            System.out.println("enable");
            this.blockCustomer = enable;
            this.lock.notify();
        }
    }

    /**
     * Точка входа в программу.
     * @param args параметры
     */
    public static void main(String[] args) {
        ProducerCustomer producerCustomer = new ProducerCustomer();
        Thread customer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerCustomer.doSomething();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                producerCustomer.changeBlock(false);
            }
        });

        customer.start();
        producer.start();
    }
}
