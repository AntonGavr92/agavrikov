package ru.job4j.io;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test CheckByteStream class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class CheckByteStreamTest {

    /**
     * Тестирование метода isNumber, с потоком в котором содержится четное число.
     */
    @Test
    public void whenInputStreamHasEvenNumberThenTrue() {
        CheckByteStream cbs = new CheckByteStream();
        String inStr = "2";
        boolean result = false;
        try (ByteArrayInputStream ba = new ByteArrayInputStream(inStr.getBytes())) {
            result = cbs.isNumber(ba);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean extend = true;
        assertThat(result, is(extend));
    }

    /**
     * Тестирование метода isNumber, с потоком в котором содержится нечетное число.
     */
    @Test
    public void whenInputStreamHasNotEvenNumberThenFalse() {
        CheckByteStream cbs = new CheckByteStream();
        String inStr = "1";
        boolean result = true;
        try (ByteArrayInputStream ba = new ByteArrayInputStream(inStr.getBytes())) {
            result = cbs.isNumber(ba);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean extend = false;
        assertThat(result, is(extend));
    }

    /**
     * Тестирование метода isNumber, с потоком в котором не содержится четное число.
     */
    @Test
    public void whenInputStreamHasNotNumberThenFalse() {
        CheckByteStream cbs = new CheckByteStream();
        String inStr = "ddss";
        boolean result = true;
        try (ByteArrayInputStream ba = new ByteArrayInputStream(inStr.getBytes())) {
            result = cbs.isNumber(ba);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean extend = false;
        assertThat(result, is(extend));
    }
}