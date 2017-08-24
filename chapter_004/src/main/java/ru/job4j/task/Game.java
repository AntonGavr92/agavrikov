package ru.job4j.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

/**
 * Created by gavrikov.a on 24/08/2017.
 */
public class Game {

    private Board board;

    private Victory victory;

    public Game(Board board, Victory victory) {
        this.board = board;
        this.victory = victory;
    }

    public void startGame() {
        boolean gameEnd = false;
        Mark player1 = new SimpleMark('x');
        Mark player2 = new SimpleMark('o');
        Mark currentPlayer = player1;
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            while (!gameEnd) {
                System.out.println(String.format("Now move player wirh char %s", currentPlayer.getView()));
                Field[][] fields = this.board.getFieldsBoard();
                for (int i = 0; i < fields.length; i++) {
                    for (int j = 0; j < fields[i].length; j++) {
                        System.out.print(fields[i][j].getMark().getView());
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
                    System.out.println(String.format("Player with char %s winner.", currentPlayer.getView()));
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

    public static void main(String[] args) {
        Game game = new Game(new SmallBoard(), new SimpleVictory());
        game.startGame();
    }
}
