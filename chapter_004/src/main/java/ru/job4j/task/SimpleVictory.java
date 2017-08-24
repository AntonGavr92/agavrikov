package ru.job4j.task;

/**
 * Created by gavrikov.a on 24/08/2017.
 */
public class SimpleVictory implements Victory {

    @Override
    public boolean playerIsWin(Board board, int lastI, int lastJ) {
        boolean result = false;
        Field[][] fields = board.getFieldsBoard();
        char charPlayer = fields[lastI][lastJ].getMark().getView();

        char[][] combinations = createCombinations(fields);

        for (int i = 0; i < combinations.length; i++){
            result = true;
            for(int j = 0; j < combinations[i].length; j++) {
                if (combinations[i][j] != charPlayer) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }

        return result;
    }

    private char[][] createCombinations(Field[][] fields) {
        char[][] combinations = new char[fields.length * 2 + 2][fields.length];
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                combinations[i][j] = fields[i][j].getMark().getView();
                combinations[j + fields[i].length][j] = fields[j][i].getMark().getView();
            }
            if(i + 1 < fields.length) {
                combinations[fields[i].length * 2 + 1][i] = fields[i][i + 1].getMark().getView();
            }
            int curCol = fields.length - 1 - i;
            combinations[fields[i].length * 2 + 1][i] = fields[i][curCol].getMark().getView();
        }
        return combinations;
    }
}
