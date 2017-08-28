package ru.job4j.task;

/**
 * SimpleVictory class.
 * @author agavrikov
 * @since 28.08.2017
 * @version 1
 */
public class SimpleVictory implements Victory {

    /**
     * Method for find victory on board.
     * @param board board
     * @param lastI row
     * @param lastJ col
     * @return true? if victory founded< else false
     */
    @Override
    public boolean playerIsWin(Board board, int lastI, int lastJ) {
        boolean result = false;
        SimpleField[][] fields = board.fields();
        char charPlayer = fields[lastI][lastJ].mark.view;

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

    /**
     * Additional method for create arrays of win positions.
     * @param fields fields
     * @return array of win positions
     */
    private char[][] createCombinations(SimpleField[][] fields) {
        char[][] combinations = new char[fields.length * 2 + 2][fields.length];
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                combinations[i][j] = fields[i][j].mark.view;
                combinations[j + fields[i].length][j] = fields[j][i].mark.view;
            }
            if(i + 1 < fields.length) {
                combinations[fields[i].length * 2 + 1][i] = fields[i][i + 1].mark.view;
            }
            int curCol = fields.length - 1 - i;
            combinations[fields[i].length * 2 + 1][i] = fields[i][curCol].mark.view;
        }
        return combinations;
    }
}
