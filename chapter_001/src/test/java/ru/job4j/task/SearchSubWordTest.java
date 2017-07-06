package ru.job4j.task;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test SearchSubWord class.
*
* @author Anton Gavrikov
* @version $Id$
* @since 0.1
*/
public class SearchSubWordTest {

	/**
	 * Test contains method with true return.
	*/
	@Test
	public void whenWordContainsSubWordThenTrue() {
		SearchSubWord searchSubWord = new SearchSubWord();
		boolean result = searchSubWord.contains("apple", "ple");
        boolean expected = true;
		assertThat(result, is(expected));
	}

	/**
	 * Test contains method with false return.
	*/
	@Test
	public void whenWordNotContainsSubWordThenFalse() {
		SearchSubWord searchSubWord = new SearchSubWord();
		boolean result = searchSubWord.contains("orange", "ngens");
        boolean expected = false;
		assertThat(result, is(expected));
	}
}