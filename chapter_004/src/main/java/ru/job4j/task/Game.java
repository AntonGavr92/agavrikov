package ru.job4j.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Game class.
 * @author agavrikov
 * @since 28.08.2017
 * @version 1
 */
public class Game {

    /**
     * Board.
     */
    private final Board board;

    /**
     * Victory.
     */
    private final Victory victory;

    /**
     * Constructor.
     * @param board Board
     * @param victory Victory
     */
    public Game(Board board, Victory victory) {
        this.board = board;
        this.victory = victory;
    }

    /**
     * method for start game.
     */
    public void startGame() {
        boolean gameEnd = false;
        SimpleMark player1 = new SimpleMark('x');
        SimpleMark player2 = new SimpleMark('o');
        SimpleMark currentPlayer = player1;
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            while (!gameEnd) {
                System.out.println(String.format("Now move player wirh char %s", currentPlayer.view));
                SimpleField[][] fields = this.board.fields();
                for (int i = 0; i < fields.length; i++) {
                    for (int j = 0; j < fields[i].length; j++) {
                        System.out.print(fields[i][j].mark.view);
                    }
                    System.out.println();
                }
                System.out.println("Enter number of row:");
                int coordR = Integer.parseInt(bf.readLine());
                System.out.println("Enter number of column:");
                int coordC = Integer.parseInt(bf.readLine());
                if (board.hasMoves()) {
                    board.move(currentPlayer, coordR, coordC);
                } else {
                    gameEnd = true;
                }
                if (victory.playerIsWin(board, coordR, coordC)) {
                    System.out.println(String.format("Player with char %s winner.", currentPlayer.view));
                    gameEnd = true;
                } else {
                    if (currentPlayer == player1) {
                        currentPlayer = player2;
                    } else {
                        currentPlayer = player1;
                    }
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * Point of start.
     * @param args params
     */
    public static void main(String[] args) {
        Game game = new Game(new SmallBoard(), new SimpleVictory());
        game.startGame();
    }
}
