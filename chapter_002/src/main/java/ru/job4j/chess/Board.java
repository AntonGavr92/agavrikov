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
    private Figure[] figures = new Figure[32];

    /**
     * Поле для хранения количества фигур.
     */
    private int countFigures = 0;

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
                this.cells[i][j] = new Cell(i, j);
                if (i == START_ROWS_PAWN_WHITE || i == START_ROWS_PAWN_BLACK) {
                    this.setFigure(new Pawn(this.cells[i][j]), i, j);
                }
            }
            if (i == START_ROWS_PAWN_WHITE - 1 || i == START_ROWS_PAWN_BLACK + 1) {
                this.setFigure(new Castle(this.cells[i][0]), i, 0);
                this.setFigure(new Knight(this.cells[i][1]), i, 1);
                this.setFigure(new Bishop(this.cells[i][2]), i, 2);
                this.setFigure(new King(this.cells[i][3]), i, 3);
                this.setFigure(new Queen(this.cells[i][4]), i, 4);
                this.setFigure(new Bishop(this.cells[i][5]), i, 5);
                this.setFigure(new Knight(this.cells[i][6]), i, 6);
                this.setFigure(new Castle(this.cells[i][7]), i, 7);
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
        if (!source.cellHasFigure()) {
            throw new FigureNotFoundException("Figure not found.");
        }
        if (dist.getRow() >= this.cells.length || dist.getCol() >= this.cells[0].length) {
            throw new ImpossibleMoveException("Field not found.");
        }

        for(int i = 0; i < this.countFigures; i++) {
            Cell[] way = this.figures[i].way(dist);
            if (way[0].getRow() == source.getRow() && way[0].getCol() == source.getCol()) {
                for (int j = 1; j < way.length; j++) {
                    if (this.cells[way[j].getRow()][way[j].getCol()].cellHasFigure()) {
                        throw new OccupiedWayException("Occupied way.");
                    }
                }
                source.setCellHasFigure(false);
                dist.setCellHasFigure(true);
                this.figures[i] = this.figures[i].clone(dist);
            }
        }
        return true;
    }

    /**
     * Сеттер для установки фигуры на доску.
     * @param figure устанавливаемая фигура
     */
    public void setFigure(Figure figure, int row, int col) {
        this.figures[this.countFigures++] = figure;
        this.cells[row][col].setCellHasFigure(true);
    }
}
