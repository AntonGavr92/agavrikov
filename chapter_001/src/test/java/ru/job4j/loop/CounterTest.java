package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Counter class.
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
*/
public class CounterTest {
	/**
	 * Тест метода add класса Counter (сначала думал использовать такое название класса - whenAddFirstNumAndAddSecondNumThenSumEvenNum,
	 * но в итоге преобразовал его, так как показалось слишком длинным), какое из двух имен лучше в данной ситуаци? Или есть более подходящий вариант?.
	*/
	@Test
	public void whenAddThenSumEvenNum() {
		Counter counter = new Counter();
		int result = counter.add(1, 10);
		int expected  = 30;
		assertThat(result, is(expected));
	}

}