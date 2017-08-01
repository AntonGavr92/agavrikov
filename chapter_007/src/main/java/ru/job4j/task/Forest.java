package ru.job4j.task;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Класс описывающий прыжки лягушки к месту назначения.
 * @author agavrikov
 * @since 01.08.2017
 * @version 1
 */
public class Forest {

    /**
     * Поле для храния поля дляпередвижения лягушки.
     */
    public final Object[][] places = new Object[10][16];

    /**
     * Поле, для хранения количества минимальных ходов лягушки.
     */
    volatile int minMoves = 0;


    /**
     * Конструктор, для инициализации препядствий лягушки.
     */
    public Forest() {
        places[8][13] = new Object();
        places[7][13] = new Object();
    }


    /**
     * Потокобезопасная очередь, в которой будем хранить лягушек на новых позициях.
     */
    private ConcurrentLinkedQueue<Frog> frogMoves = new ConcurrentLinkedQueue<Frog>();

    /**
     * Точка входа в программу.
     * @param args параметры
     */
    public static void main(String[] args) {
        Forest forest = new Forest();
        Frog frog = new Frog(6, 10, 0);
        forest.findWay(frog);
        forest.createThread().start();
        forest.createThread().start();
        forest.createThread().start();
    }

    /**
     * Метод для создания нового потока, для обхода очереди.
     * @return новый поток.
     */
    public Thread createThread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                //таймер, приложение вычисляет возможные пути 10 секунд.
                //возможен вариант работы не по таймеру, но если есть вероятность того что поток, который будет отвечать за наименьшее количество прыжков
                //не будет иметь достаточно процессорного времени, возможна(хоть и маловероятна) ситуация, когда поток имеющий более длительный путь займет
                //количество мин прыжков. За счет таймера такая ситуация исключена.
                long timeStart = System.currentTimeMillis();
                while (frogMoves.size() > 0 && System.currentTimeMillis() - timeStart < 10000) {
                    findWay(frogMoves.poll());
                }
            }
        });
    }

    /**
     * Метод для прохода всех возможных полей лягушки, с целью попасть в нужную нам точку.
     * @param frog лягушка
     */
    public void findWay(Frog frog) {
        for (Move move : getMove(frog)) {
            frogMoves.add(frog.setNewPosition(move.getRow(), move.getCol()));
            if(move.getRow() == 9 && move.getCol() == 8) {
                synchronized (this.places) {
                    if(this.minMoves == 0 || this.minMoves > frog.getMoves() + 1) {
                        this.minMoves = frog.getMoves() + 1;
                        System.out.println(this.minMoves);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Вспомогательный метод, для получения всех возможных путей у параметра frog. Учитывается что поле круглое.
     * @param frog лягушка
     * @return список всех допустимых ходов
     */
    public ArrayList<Move> getMove(Frog frog) {
        ArrayList<Move> moves = new ArrayList<Move>();
        if (frog.getRowPos() < places.length && frog.getColPos() + 3 < places[0].length && places[frog.getRowPos()][getIndexCircleMove(frog.getColPos() + 3)] == null){
            moves.add(new Move(frog.getRowPos(), getIndexCircleMove(frog.getColPos() + 3)));
        }

        if (frog.getRowPos() + 1 < places.length && places[frog.getRowPos() + 1][getIndexCircleMove(frog.getColPos() + 2)] == null){
            moves.add(new Move(frog.getRowPos() + 1, getIndexCircleMove(frog.getColPos() + 2)));
        }
        if (frog.getRowPos() + 2 < places.length && places[frog.getRowPos() + 2][getIndexCircleMove(frog.getColPos() + 1)] == null){
            moves.add(new Move(frog.getRowPos() + 2, getIndexCircleMove(frog.getColPos() + 1)));
        }

        if (frog.getRowPos() - 1 >= 0 && places[frog.getRowPos() - 1][getIndexCircleMove(frog.getColPos() + 2)] == null){
            moves.add(new Move(frog.getRowPos() - 1, getIndexCircleMove(frog.getColPos() + 2)));
        }
        if (frog.getRowPos() - 2 >= 0 && places[frog.getRowPos() - 2][getIndexCircleMove(frog.getColPos() + 1)] == null){
            moves.add(new Move(frog.getRowPos() - 2, getIndexCircleMove(frog.getColPos() + 1)));
        }
        return moves;
    }

    /**
     * Вспомогательный метод, для возможности ходить по кругу.
     * @param newPosCol новое положение в колонке
     * @return число не превышающее размеры колонок массива(леса).
     */
    public int getIndexCircleMove(int newPosCol) {
        int result = newPosCol;
        if(newPosCol >= places[0].length) {
            result = newPosCol - places[0].length;
        }
        if(result >= places[0].length || result < 0){
            System.out.println(result);
        }
        return result;
    }


}
