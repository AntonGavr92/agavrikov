package ru.job4j.jmm;

/**
 * Класс отображающий проблемы многопоточности.
 * @author agavrikov
 * @since 24.07.2017
 * @version 1
 */
public class Problem {

    /**
     * Поле для хранения количества операций.
     */
    public int countOperation = 0;

     /**
     * Поле для хранения проверочного значения.
     */
    private int check = 0;

    /**
     * Метод для инкремента.
     */
    public void increaseCheck() {
        check++;
    }

    /**
     * геттер поля check.
     * @return поле check
     */
    public long getCheck() {
        return check;
    }

    /**
     * Метод реализующий проблему при которой метод getСheck() получает значение и далее с помощью increaseСheck() присходит инкремент.
     * Суть проблемы в том, что getСheck() в одном из потоков может вернуть нам 999, а другой поток в этот момент инкрементирует данное значение.
     * Далее второй поток инкрементирует значение. Итогом может явится результат при котром в конце работы программы в поле хранится значение большее чем 1000.
     * Так же в этом можно убедится, засыпив один из потоков внутри цикла. Так же о проблеме может свидетельствовать счетчик операций инкремента countOperation.
     * @param args параметры.
     */
    public static void main(String[] args) {
        Problem problem = new Problem();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (problem.getCheck() < 1000) {
                    problem.increaseCheck();
                    problem.countOperation++;
                }
                System.out.println(String.format("1 thread %s", problem.check));
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (problem.getCheck() < 1000) {
                    problem.increaseCheck();
                    problem.countOperation++;
                }
                System.out.println(String.format("2 thread %s", problem.check));
            }
        });
        thread.start();
        thread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //problem.check++;
        System.out.println(String.format("main thread %s", problem.check));
        System.out.println(String.format("countOperation %s", problem.countOperation));
    }
}
