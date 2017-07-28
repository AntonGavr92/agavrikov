package ru.job4j.task;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс описывающий игру bomberman.
 * @author agavrikov
 * @since 27.07.2017
 * @version 1
 */
public class Bomberman {

    /**
     * Поле для хранения игрового поля.
     */
    private final ReentrantLock[][] board;

    /**
     * Инициализация игрового поля.
     * @param row строки
     * @param cols столбцы
     */
    public Bomberman(int row, int cols) {
        this.board = new ReentrantLock[row][cols];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cols; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    public void createThread(Bomberman bomberman) {
        boolean play = true;
        Random rand = new Random();
        Thread heroThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (play) {
                    int newRow = rand.nextInt(2);
                    int newCol = rand.nextInt(2);
                    if (bomberman.board[newRow][newCol].tryLock()) {
                        System.out.println(String.format("Второй поток занял позицию %s %s", newRow, newCol));
                        try {
                            Thread.sleep(20000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        bomberman.board[newRow][newCol].unlock();
                    }
                }
            }
        });
        heroThread.start();
    }

    public void createHeroThread(Bomberman bomberman) {
        Random rand = new Random();
        boolean play = true;
        Hero hero = new Hero(0, 0);
        while (play) {
            //спорный вариант, возможно здесь лучше использовать producer customer
            //движение каждую секунду
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int newRow = 0;
            int newCol = 0;
            while (hero.col == newCol && hero.row == newRow) {
                newRow = rand.nextInt(2);
                newCol = rand.nextInt(2);
            }

            try {
                if (bomberman.board[newRow][newCol].tryLock(500, TimeUnit.MILLISECONDS)) {
                    if (bomberman.board[hero.row][hero.col].isLocked()) {
                        bomberman.board[hero.row][hero.col].unlock();
                    }
                    hero = hero.changeField(newRow, newCol);
                    System.out.println(String.format("Игрок занял позицию %s %s", hero.row, hero.col));
                } else {
                    System.out.println(String.format("Игроку не удалось занять позицию %s %s", hero.row, hero.col));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Точка входа в программу.
     * @param args Набор параметров
     */
    public static void main(String[] args) {
        Bomberman bomberman = new Bomberman(2, 2);
        bomberman.createHeroThread(bomberman);
        bomberman.createThread(bomberman);
    }

}
