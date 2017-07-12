package ru.job4j.chess;

/**
 * Класс для описания шахматной доски.
 * @author agavrikov
 * @since 11.07.2017
 * @version 1
 */
public class Board {

    /**
     * Поле для хранения полей шахматной доски.
     */
    private Cell[][] cells = new Cell[8][8];

    /**
     * константа для хранения стартовых строк шахматной доски у пешек белого цвета.
     */
    private static final int START_ROWS_PAWN_WHITE = 1;

    /**
     * константа для хранения стартовых строк шахматной доски у пешек черного цвета.
     */
    private static final int START_ROWS_PAWN_BLACK = 6;

    /**
     * Поле для хранения фигур.
     */
    //private Figure[] figures = new Figure[32];

    /**
     * Геттер ячеек доски.
     * @return массив ячеек на доске
     */
    public Cell[][] getCells() {
        return this.cells;
    }


    /**
     * Метод для заполнения доски по умолчанию.
     */
    public void fillBoard() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                this.cells[i][j] = new Cell(this, i, j);
                if (i == START_ROWS_PAWN_WHITE || i == START_ROWS_PAWN_BLACK) {
                    this.cells[i][j].setFigure(new Pawn(this.cells[i][j]));
                }
            }
            if (i == START_ROWS_PAWN_WHITE - 1 || i == START_ROWS_PAWN_BLACK + 1) {
                this.cells[i][0].setFigure(new Castle(this.cells[i][0]));
                this.cells[i][1].setFigure(new Knight(this.cells[i][1]));
                this.cells[i][2].setFigure(new Bishop(this.cells[i][2]));
                this.cells[i][3].setFigure(new King(this.cells[i][3]));
                this.cells[i][4].setFigure(new Queen(this.cells[i][4]));
                this.cells[i][5].setFigure(new Bishop(this.cells[i][5]));
                this.cells[i][6].setFigure(new Knight(this.cells[i][6]));
                this.cells[i][7].setFigure(new Castle(this.cells[i][7]));
            }
        }
    }

    /**
     * Метод, с помощью которого производится ход на  доске.
     * @param source - ячейка, с которой производится ход.
     * @param dist - ячейка, на которую производится ход.
     * @return в случае успешного выполнения метод вернет true.
     * @throws ImpossibleMoveException исключение о невозможности сделать такой ход
     * @throws OccupiedWayException исключение о том что на пути движения есть фигура
     * @throws FigureNotFoundException исключение о том что ячейка, с которой собираемся ходить не содержит фигуру
     */
    public boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        if (source.getFigure() == null) {
            throw new FigureNotFoundException("Figure not found.");
        }
        if (dist.getRow() >= this.cells.length || dist.getCol() >= this.cells[0].length) {
            throw new ImpossibleMoveException("Field not found.");
        }

        Cell[] cellsWay = source.getFigure().way(dist);
        cellsWay[cellsWay.length - 1].setFigure(source.getFigure());
        dist.setFigure(null);
        return true;
    }
}
