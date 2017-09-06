package ru.job4j.additional;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by gavrikov.a on 06/09/2017.
 */
public class MaxVectorSearcherTest {

    @Test
    public void whenBoardHasMaxLengthVectorNThanFindMaxVectorIsSame() {
        MaxVectorSearcher searcher = new MaxVectorSearcher(createFields());
        int result = searcher.findMaxVector();
        int expected = 2;
        assertThat(result, is(expected));
    }

    private Field[][] createFields() {
        Field[][] fields = new Field[3][3];

        fields[0][0] = new Field(1);
        fields[0][1] = new Field(1);
        fields[0][2] = new Field(0);

        fields[1][0] = new Field(0);
        fields[1][1] = new Field(1);
        fields[1][2] = new Field(0);

        fields[2][0] = new Field(1);
        fields[2][1] = new Field(0);
        fields[2][2] = new Field(1);

        return fields;
    }

}