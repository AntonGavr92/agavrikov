package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test SortDataFile class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class SortDataFileTest {

    /**
     * Метод для тестирования метода sort.
     */
    @Test
    public void whenSortDataFileThenDataFileResultIsSort() {
        SortDataFile sdf = new SortDataFile();
        File resultFile = new File("C:/test/result.txt");
        sdf.sort(new File("C:/test/testMiddle.txt"), resultFile);
        boolean result = true;
        try (RandomAccessFile rac = new RandomAccessFile(resultFile, "r")) {
            String res1 = rac.readLine();
            while (res1 != null) {
                String res2 = rac.readLine();
                if (res2 == null) {
                    break;
                }
                if (res1.length() > res2.length()) {
                    result = false;
                    break;
                } else {
                    res1 = res2;
                }
            }
            assertThat(result, is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}