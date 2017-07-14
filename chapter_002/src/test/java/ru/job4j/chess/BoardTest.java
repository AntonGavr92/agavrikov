package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Test Board class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {
    /**
     * Тестирование метода move.
     */
    @Test
    public void whenMoveAndFigureExistAndWayNotOccupedAndDistFieldExistThenTrue() {
        Board board = new Board();
        Cell[][] cells = board.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        board.setFigure(new Bishop(cells[4][2]), 4, 2);
        boolean result = false;
        try {
            result = board.move(cells[4][2], cells[2][0]);
        } catch (FigureNotFoundException fnf) {
            System.out.println(fnf);
        } catch (ImpossibleMoveException im) {
            System.out.println(im);
        } catch (OccupiedWayException ow) {
            System.out.println(ow);
        }
        boolean expected = true;
        assertThat(result, is(expected));
    }


    /**
     * Тестирование метода move.
     */
    @Test
    public void whenMoveAndFigureExistAndWayOccupedAndDistFieldExistThenFalse() {
        Board board = new Board();
        Cell[][] cells = board.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        board.setFigure(new Bishop(cells[3][1]),3, 1);
        board.setFigure(new Bishop(cells[4][2]), 4, 2);
        boolean result = false;
        try {
            result = board.move(cells[4][2], cells[2][0]);
        } catch (FigureNotFoundException fnf) {
            System.out.println(fnf);
        } catch (ImpossibleMoveException im) {
            System.out.println(im);
        } catch (OccupiedWayException ow) {
            System.out.println(ow);
        }
        boolean expected = false;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода move.
     */
    @Test
    public void whenMoveAndFigureNotExistAndWayNotOccupedAndDistFieldExistThenFalse() {
        Board board = new Board();
        Cell[][] cells = board.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        board.setFigure(new Bishop(cells[3][1]), 3,1);
        board.setFigure(new Bishop(cells[4][2]),4, 2);
        boolean result = false;
        try {
            result = board.move(cells[5][2], cells[2][0]);
        } catch (FigureNotFoundException fnf) {
            System.out.println(fnf);
        } catch (ImpossibleMoveException im) {
            System.out.println(im);
        } catch (OccupiedWayException ow) {
            System.out.println(ow);
        }
        boolean expected = false;
        assertThat(result, is(expected));
    }
}