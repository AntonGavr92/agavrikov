package ru.job4j.chess;

/**
 * Абстрактный класс, для описания общего у всех фигур шахматной доски.
 * @author agavrikov
 * @since 11.07.2017
 * @version 1
 */
abstract class Figure {

    /**
     * Поле для хранения текущей позиции фигуры.
     */
    private Cell position;

    /**
     * Конструктор.
     * @param position - поле для установки фигуры на него.
     */
     Figure(Cell position) {
        this.position = position;
        position.setCellHasFigure(true);
    }

    /**
     * Метод для получения ячейки на которой стоит фигура.
     * @return ячека на которой стоит фигура.
     */
    public Cell getPosition() {
         return this.position;
    }

    /**
     * Метод для изменения яччейки у фигуры.
     * @param newPosition - новая ячейка.
     */
    public void setPosition(Cell newPosition) {
        this.position.setCellHasFigure(false);
        this.position = newPosition;
        this.position.setCellHasFigure(true);
    }

    /**
     * /**
     * метод для получения полей, которые необходимо пройти фигуре.
     * @param dist - поле в которое мы хотим поместить фигуру
     * @return - массив полей, которые должна пройти фигура
     * @throws ImpossibleMoveException исключение о невозможности движения фигуры
     * @throws OccupiedWayException исключение о блокировки движения другой фигуры
     */
    abstract Cell[] way(Cell dist) throws ImpossibleMoveException, OccupiedWayException;
}

/**
 * Абстрактный класс, описывающий пешку.
 * @author agavrikov
 * @since 12.07.2017
 * @version 1
 */
class Pawn extends Figure {

    /**
     * Конструктор.
     * @param position - поле для установки фигуры на него.
     */
     Pawn(Cell position) {
        super(position);
     }

    /**
     * метод для получения полей, которые необходимо пройти фигуре.
     * @param dist - поле в которое мы хотим поместить фигуру
     * @return - массив полей, которые должна пройти фигура
     */
    public Cell[] way(Cell dist) {
        return new Cell[]{};
    }

}

/**
 * Класс, описывающий ладью.
 * @author agavrikov
 * @since 12.07.2017
 * @version 1
 */
class Castle extends Figure {

    /**
     * Конструктор.
     * @param position - поле для установки фигуры на него.
     */
     Castle(Cell position) {
        super(position);
    }

    /**
     * метод для получения полей, которые необходимо пройти фигуре.
     * @param dist - поле в которое мы хотим поместить фигуру
     * @return - массив полей, которые должна пройти фигура
     */
    public Cell[] way(Cell dist) {
        return new Cell[]{};
    }

}

/**
 * Класс, описывающий коня.
 * @author agavrikov
 * @since 12.07.2017
 * @version 1
 */
class Knight extends Figure {

    /**
     * Конструктор.
     * @param position - поле для установки фигуры на него.
     */
     Knight(Cell position) {
        super(position);
    }

    /**
     * метод для получения полей, которые необходимо пройти фигуре.
     * @param dist - поле в которое мы хотим поместить фигуру
     * @return - массив полей, которые должна пройти фигура
     */
    public Cell[] way(Cell dist) {
        return new Cell[]{};
    }

}

/**
 * Класс, описывающий слона.
 * @author agavrikov
 * @since 12.07.2017
 * @version 1
 */
class Bishop extends Figure {

    /**
     * Конструктор.
     * @param position - поле для установки фигуры на него.
     */
     Bishop(Cell position) {
        super(position);
    }

    /**
     * метод для получения полей, которые необходимо пройти фигуре.
     * @param dist - поле в которое мы хотим поместить фигуру
     * @return - массив полей, которые должна пройти фигура
     */
    @Override
    public Cell[] way(Cell dist) throws ImpossibleMoveException, OccupiedWayException {
        Cell[][] cellsBoard = dist.getBoard().getCells();
        int currentCol = this.getPosition().getCol();
        int currentRow = this.getPosition().getRow();

        int distCol  = dist.getCol();
        int distRow  = dist.getRow();

        Cell[] wayCells = new Cell[Math.abs(distRow - currentRow)];
        int wayCellsCounter = 0;
        boolean cellHasFigure = false;

        if (currentRow >= distRow && Math.abs(currentCol - distCol) ==  Math.abs(distRow - currentRow)) {
            //Можно ходить, получим все ячейки для проверки их занятостью
            while (currentCol != distCol && currentRow != distRow) {
                cellHasFigure = cellsBoard[--currentRow][--currentCol].cellHasFigure();
                if (cellHasFigure) {
                   throw new OccupiedWayException("Occupied way.");
                }
                wayCells[wayCellsCounter++] = cellsBoard[currentRow][currentCol];
            }
        } else if (currentRow <= distRow && currentCol + distCol == distRow + currentRow) {
            while (currentCol != distCol && currentRow != distRow) {
                cellHasFigure = cellsBoard[++currentRow][++currentCol].cellHasFigure();
                if (cellHasFigure) {
                    throw new OccupiedWayException("Occupied way.");
                }
                wayCells[wayCellsCounter++] = cellsBoard[currentRow][currentCol];
            }
        } else {
            throw new ImpossibleMoveException("Can not move on this field.");
        }
        return wayCells;
    }

}

/**
 * Класс, описывающий короля.
 * @author agavrikov
 * @since 12.07.2017
 * @version 1
 */
class King extends Figure {

    /**
     * Конструктор.
     * @param position - поле для установки фигуры на него.
     */
     King(Cell position) {
        super(position);
    }

    /**
     * метод для получения полей, которые необходимо пройти фигуре.
     * @param dist - поле в которое мы хотим поместить фигуру
     * @return - массив полей, которые должна пройти фигура
     */
    public Cell[] way(Cell dist) {
        return new Cell[]{};
    }

}

/**
 * Класс, описывающий королеву.
 * @author agavrikov
 * @since 12.07.2017
 * @version 1
 */
class Queen extends Figure {

    /**
     * Конструктор.
     * @param position - поле для установки фигуры на него.
     */
     Queen(Cell position) {
        super(position);
    }

    /**
     * метод для получения полей, которые необходимо пройти фигуре.
     * @param dist - поле в которое мы хотим поместить фигуру
     * @return - массив полей, которые должна пройти фигура
     */
    public Cell[] way(Cell dist) {
        return new Cell[]{};
    }

}
