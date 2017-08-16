package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test StreamRemoveWords class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class StreamRemoveWordsTest {

    /**
     * Тестирование метода dropAbuses.
     */
    @Test
    public void whenInputStreamHasAbuseWordsThenOutputStreamDontHasSameWords() {
        StreamRemoveWords test = new StreamRemoveWords();
        String inStr = "abc gf abc dfgd";
        try (ByteArrayOutputStream res = new ByteArrayOutputStream()) {
            test.dropAbuses(new ByteArrayInputStream(inStr.getBytes()), res, new String[]{"abc", "dfg"});
            String result = res.toString();
            assertThat(result, is(" gf  d"));
        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}