package ru.job4j.tdd;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for test SimpleGenerator class.
 * @author agavrikov
 * @since 24.08.2017
 * @version 1
 */
public class SimpleGeneratorTest {

    /**
     * Method for test method getGenerateString with all correct data.
     * @throws Exception exception
     */
    @Test
    public void whenValueWithSpecWordsAndMapAliasesIsNotEmptyThenReturnStringWithReplaceSpecWordsToAliasesFromMap() throws Exception {
        SimpleGenerator sg = new SimpleGenerator();
        Map<String, String> aliases = new HashMap<>();
        aliases.put("name", "Anton");
        aliases.put("subject", "you");

        String result = sg.getGenerateString("I am a ${name}, Who are ${subject}?", aliases);
        String expected = "I am a Anton, Who are you?";

        assertThat(result, is(expected));
    }

    /**
     * Method for test method getGenerateString with spec word in start value and map has not this word.
     */
    @Test
    public void whenValueWithSpecWordsAndMapAliasesIsNotHaveSpecWordThenException() {
        SimpleGenerator sg = new SimpleGenerator();
        Map<String, String> aliases = new HashMap<>();
        aliases.put("name", "Anton");
        aliases.put("subject", "you");

        String result;
        try {
            result = sg.getGenerateString("I am a ${name}, Who are ${subjects}?", aliases);
        } catch (Exception e) {
            result = null;
        }
        String expected = null;

        assertThat(result, is(expected));
    }

    /**
     * Method for test method getGenerateString with spec word in map, but start value has not this.
     */
    @Test
    public void whenValueWithSpecWordsAndMapAliasesHaveNotUsedSpecWordThenException() {
        SimpleGenerator sg = new SimpleGenerator();
        Map<String, String> aliases = new HashMap<>();
        aliases.put("name", "Anton");
        aliases.put("subject", "you");
        aliases.put("subjects", "you");

        String result;
        try {
            result = sg.getGenerateString("I am a ${name}, Who are ${subjects}?", aliases);
        } catch (Exception e) {
            result = null;
        }
        String expected = null;

        assertThat(result, is(expected));
    }


}