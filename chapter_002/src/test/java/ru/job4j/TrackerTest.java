package ru.job4j;

import org.junit.Test;
import java.util.Date;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Tracker class.
 *
 * @author Anton Gavrikov
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {

    /**
     * Тестирование метода add.
     */
    @Test
    public void whenAdd200ItemsThenMaxItemsCount100() {
        Date date = new Date();
        String[] comments = {"Test comment"};
        Tracker tracker = new Tracker();
        for (int i = 0; i < 200; i++) {
            Item item = new Item(String.valueOf(i), "Test", "Test desc", date.getTime(), comments);
            tracker.add(item);
        }

        Item result = tracker.findById("100");
        Item expected = null;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода update.
     */
    @Test
    public void whenUpdateNameItemId1ThenChangeNameItemId1() {
        Date date = new Date();
        String[] comments = {"Test comment"};
        Tracker tracker = new Tracker();
        Item item = new Item("1", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);

        Item updateItem = new Item("1", "Test update", "Test desc", date.getTime(), comments);
        tracker.update(updateItem);

        String result = tracker.findById("1").getName();
        String expected = "Test update";
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода delete.
     */
    @Test
    public void whenDeleteItemWithId1ThenTrackerFindById1IsNull() {
        Date date = new Date();
        String[] comments = {"Test comment"};
        Tracker tracker = new Tracker();
        Item item = new Item("1", "Test", "Test desc", date.getTime(), comments);
        Item itemResult = tracker.add(item);

        tracker.delete(item);

        Item result = tracker.findById("1");
        Item expected = null;
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода delete.
     */
    @Test
    public void whenDeleteItemWithId1ThenTrackerFindById2IsNotNull() {
        Date date = new Date();
        String[] comments = {"Test comment"};
        Tracker tracker = new Tracker();
        Item item = new Item("1", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);

        Item item2 = new Item("2", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        tracker.delete(item);

        Item result = tracker.findById("2");
        Item expected = item2;
        assertThat(result, is(item2));
    }

    /**
     * Тестирование метода findAll.
     */
    @Test
    public void whenAdd3ItemsAndDelete1ItemAndFindAllThen2Items() {
        Date date = new Date();
        String[] comments = {"Test comment"};
        Tracker tracker = new Tracker();
        Item item = new Item("1", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);

        Item item2 = new Item("2", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        Item item3 = new Item("3", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item3);

        tracker.delete(item);

        Item[] result = tracker.findAll();
        Item[] expected = {item2, item3};
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода findByName.
     */
    @Test
    public void whenAdd2ItemAndFindByNameTest2ThenSecondItem() {
        Date date = new Date();
        String[] comments = {"Test comment"};
        Tracker tracker = new Tracker();
        Item item = new Item("1", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);

        Item item2 = new Item("2", "Test2", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        String result = tracker.findByName("Test2")[0].getId();
        String expected = "2";
        assertThat(result, is(expected));
    }

    /**
     * Тестирование метода findById.
     */
    @Test
    public void whenAdd2ItemAndFindById1ThenFirstItem() {
        Date date = new Date();
        String[] comments = {"Test comment"};
        Tracker tracker = new Tracker();
        Item item = new Item("1", "Test", "Test desc", date.getTime(), comments);
        tracker.add(item);

        Item item2 = new Item("2", "Test2", "Test desc", date.getTime(), comments);
        tracker.add(item2);

        Item result = tracker.findById("1");
        Item expected = item;
        assertThat(result, is(expected));
    }

}