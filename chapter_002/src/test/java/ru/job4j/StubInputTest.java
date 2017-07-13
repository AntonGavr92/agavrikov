package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
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
        ArrayList<String> input = new ArrayList<String>();
        input.add("0");
        input.add("Test");
        input.add("Test description");
        input.add("6");
        StubInput inputObj = new StubInput(input);
        StartUI program = new StartUI(inputObj, tracker);
        program.init();
        String result = tracker.findByName("Test").get(0).getName();
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
        ArrayList<String> comments = new ArrayList<String>();
        comments.add("Test comment");
        Item item = new Item("1", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);
        Item item2 = new Item("2", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        ArrayList<String> input = new ArrayList<String>();
        input.add("1");
        input.add("6");
        StubInput inputObj = new StubInput(input);
        StartUI program = new StartUI(inputObj, tracker);
        program.init();
        int result = tracker.findByName("Test").size();
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
        ArrayList<String> comments = new ArrayList<String>();
        comments.add("Test comment");
        Item item = new Item("1", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);
        Item item2 = new Item("2", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        ArrayList<String> input = new ArrayList<String>();
        input.add("4");
        input.add("1");
        input.add("6");
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
        ArrayList<String> comments = new ArrayList<String>();
        comments.add("Test comment");
        Item item = new Item("0", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);
        Item item2 = new Item("1", "Test2", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        ArrayList<String> input = new ArrayList<String>();
        input.add("5");
        input.add("Test");
        input.add("6");
        StubInput inputObj = new StubInput(input);
        StartUI program = new StartUI(inputObj, tracker);
        program.init();
        Item result = tracker.findByName("Test2").get(0);
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
        ArrayList<String> comments = new ArrayList<String>();
        comments.add("Test comment");
        Item item = new Item("1", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);
        Item item2 = new Item("2", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        ArrayList<String> input = new ArrayList<String>();
        input.add("3");
        input.add("0");
        input.add("6");
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
        ArrayList<String> comments = new ArrayList<String>();
        comments.add("Test comment");
        Item item = new Item("0", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);
        Item item2 = new Item("1", "Test2", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        ArrayList<String> input = new ArrayList<String>();
        input.add("2");
        input.add("0");
        input.add("Test update");
        input.add("Test desc");
        input.add("test comment");
        input.add("6");
        StubInput inputObj = new StubInput(input);
        StartUI program = new StartUI(inputObj, tracker);
        program.init();
        String result = tracker.findByName("Test update").get(0).getId();
        String expected = "0";
        assertThat(result, is(expected));
    }
}