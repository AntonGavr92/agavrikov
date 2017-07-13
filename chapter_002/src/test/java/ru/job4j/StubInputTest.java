package ru.job4j;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test module chapter_002(tracker).
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class StubInputTest {

    /**
     * Тестирование добавления заявки.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        String[] input = {"0", "Test", "Test description", "6"};
        StubInput inputObj = new StubInput(input);
        StartUI program = new StartUI(inputObj, tracker);
        program.init();
        String result = tracker.findByName("Test")[0].getName();
        String expected = "Test";
        assertThat(result, is(expected));
    }

    /**
     * Тестирование пользовательского ввода для получения всех заявок.
     */
    @Test
    public void whenUserSearchAllItemsThenTrackerHasAllItems() {
        Tracker tracker = new Tracker();

        Date date = new Date();
        String[] comments = {"Test comment"};
        Item item = new Item("1", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);
        Item item2 = new Item("2", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        String[] input = {"1", "6"};
        StubInput inputObj = new StubInput(input);
        StartUI program = new StartUI(inputObj, tracker);
        program.init();
        int result = tracker.findByName("Test").length;
        int expected = 2;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование пользовательского ввода для получения заявки по id.
     */
    @Test
    public void whenTrackerHasItemId1AndUserSearchItemWithId1ThenTrackerHasItemWithId1() {
        Tracker tracker = new Tracker();

        Date date = new Date();
        String[] comments = {"Test comment"};
        Item item = new Item("1", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);
        Item item2 = new Item("2", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        String[] input = {"4", "1", "6"};
        StubInput inputObj = new StubInput(input);
        StartUI program = new StartUI(inputObj, tracker);
        program.init();
        Item result = tracker.findById("1");
        Item expected = item;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование пользовательского ввода для получения заявки по имени.
     */
    @Test
    public void whenTrackerHasItemWithNameAndUserSearchItemWithSameNameThenTrackerHasItemWithSameName() {
        Tracker tracker = new Tracker();

        Date date = new Date();
        String[] comments = {"Test comment"};
        Item item = new Item("0", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);
        Item item2 = new Item("1", "Test2", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        String[] input = {"5", "Test", "6"};
        StubInput inputObj = new StubInput(input);
        StartUI program = new StartUI(inputObj, tracker);
        program.init();
        Item result = tracker.findByName("Test2")[0];
        Item expected = item2;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование пользовательского ввода удаления заявки по id.
     */
    @Test
    public void whenTrackerHasItemId1AndUserDeleteItemWithId1ThenTrackerHasNotItemWithId1() {
        Tracker tracker = new Tracker();

        Date date = new Date();
        String[] comments = {"Test comment"};
        Item item = new Item("1", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);
        Item item2 = new Item("2", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        String[] input = {"3", "0", "6"};
        StubInput inputObj = new StubInput(input);
        StartUI program = new StartUI(inputObj, tracker);
        program.init();
        Item result = tracker.findById("1");
        Item expected = null;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование пользовательского ввода изменения заявки по id.
     */
    @Test
    public void whenTrackerHasItemId1AndUserUpdateItemWithId1ThenTrackerUpdatedItemWithId1() {
        Tracker tracker = new Tracker();

        Date date = new Date();
        String[] comments = {"Test comment"};
        Item item = new Item("0", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);
        Item item2 = new Item("1", "Test2", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        String[] input = {"2", "0", "Test update", "Test desc", "test comment", "6"};
        StubInput inputObj = new StubInput(input);
        StartUI program = new StartUI(inputObj, tracker);
        program.init();
        String result = tracker.findByName("Test update")[0].getId();
        String expected = "0";
        assertThat(result, is(expected));
    }
}