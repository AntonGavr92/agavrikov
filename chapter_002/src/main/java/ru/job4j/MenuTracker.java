package ru.job4j;

import java.util.ArrayList;
import java.util.Date;


/**
 * Class описывающий меню трекера.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class MenuTracker {

    /**
     * Массив с набором действиий, соответствующих массиву menuItems.
     */
    private ArrayList<UserAction> actions = new ArrayList<UserAction>();

    /**
     * Конструктор по умолчанию.
     */
    public MenuTracker() {
        this.actions.add(new ActionAdd("Add task", 0));
        this.actions.add(new ActionShowAll("Show all tasks", 1));
        this.actions.add(new ActionEdit("Edit task", 2));
        this.actions.add(new ActionDelete("Delete task", 3));
        this.actions.add(new ActionFindById("Find task by id", 4));
        this.actions.add(new ActionFindByName("Find task by name", 5));
        this.actions.add(new ActionExit("Exit", 6));
    }

    /**
     * Геттер пунктов меню.
     * @return объект UserAction
     */
    public ArrayList<UserAction> getActions() {
        return this.actions;
    }

    /**
     * Метод, возвращающий элемент по индексу (введенного пользователем).
     * @param index - индекс в массиве actions
     * @return - Объект Action
     */
    public UserAction select(int index) {
        return this.actions.get(index);
    }

    /**
     * Класс описывающий событие добавление задачи в трекер.
     * @author agavrikov
     * @since 09.07.2017
     * @version 1
     */
    public class ActionAdd extends BaseAction {

        /**
         * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
         * @param name - наименование пункта меню
         * @param key - индекс пункта меню
         */
        public ActionAdd(String name, int key) {
            super(name, key);
        }

        /**
         * Метод, добавляющий новую задачу в трекер.
         * @param tracker - трекер
         * @param input - ввод данных
         */
        @Override
        public boolean execute(Tracker tracker, Input input) {
            Date date = new Date();
            String name = input.ask("Enter the task's name: ");
            String description = input.ask("Enter the task's description: ");

            ArrayList<Item> items = tracker.findAll();
            Integer id = 0;
            if (items.size() > 0) {
                id = Integer.parseInt(items.get(items.size() - 1).getId());
                id++;
            }
            Item resultItem = tracker.add(new Item(id.toString(), name, description, date.getTime()));
            input.print(String.format("Task %s with id %s was created.", resultItem.getName(), resultItem.getId()));
            return false;
        }
    }

    /**
     * Class, описывающий удаление заявки.
     * @author agavrikov
     * @since 09.07.2017
     * @version 1
     */
    public static class ActionDelete extends BaseAction {

        /**
         * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
         * @param name - наименование пункта меню
         * @param key - индекс пункта меню
         */
        public ActionDelete(String name, int key) {
            super(name, key);
        }

        /**
         * Метод, добавляющий новую задачу в трекер.
         * @param tracker - трекер
         * @param input - ввод данных
         */
        @Override
        public boolean execute(Tracker tracker, Input input) {
            ArrayList<Item> items = tracker.findAll();
            ArrayList<Integer> itemsRange = new ArrayList<Integer>();
            for (int i = 0; i < items.size(); i++) {
                input.print(String.format("%s. Task %s %s", i, items.get(i).getId(), items.get(i).getName()));
                itemsRange.add(i);
            }
            if (items.size() > 0) {
                tracker.delete(items.get(input.ask("Select: ", itemsRange)));
            }
            return false;
        }
    }
}

/**
 * Class, вывод всех заявок трекера.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
class ActionShowAll extends BaseAction {

    /**
     * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
     * @param name - наименование пункта меню
     * @param key - индекс пункта меню
     */
    ActionShowAll(String name, int key) {
        super(name, key);
    }

    /**
     * Метод, выводящий все заявки трекера.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public boolean execute(Tracker tracker, Input input) {
        for (Item item : tracker.findAll()) {
            input.print(String.format("Task %s name %s", item.getId(), item.getName()));
        }
        return false;
    }
}

/**
 * Class, описывающий изменение заявки.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
class ActionEdit extends BaseAction {

    /**
     * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
     * @param name - наименование пункта меню
     * @param key - индекс пункта меню
     */
    ActionEdit(String name, int key) {
        super(name, key);
    }

    /**
     * метод изменяющий выбранную заявку.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public boolean execute(Tracker tracker, Input input) {
        ArrayList<Item> items = tracker.findAll();
        ArrayList<Integer> itemsRange = new ArrayList<Integer>();
        for (int i = 0; i < items.size(); i++) {
            input.print(String.format("%s. Task %s %s", i, items.get(i).getId(), items.get(i).getName()));
            itemsRange.add(i);
        }
        if (items.size() > 0) {
            Item item = items.get(input.ask("Select task: ", itemsRange));
            ArrayList<String> comments = new ArrayList<String>();
            String name = input.ask("Enter new name: ");
            String description = input.ask("Enter new description: ");
            comments.add(input.ask("Enter comment: "));
            tracker.update(new Item(item.getId(), name,  description, item.getCreated(), comments));
        }
       return false;
    }
}


/**
 * Class, поиск заявки по id.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
class ActionFindById extends BaseAction {

    /**
     * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
     * @param name - наименование пункта меню
     * @param key - индекс пункта меню
     */
    ActionFindById(String name, int key) {
        super(name, key);
    }

    /**
     * Метод, для поиска заявки по id и вывода данных по ней пользователю.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public boolean execute(Tracker tracker, Input input) {
        String id = input.ask("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            input.print(String.format("Found item - %s %s", item.getId(), item.getName()));
        } else {
            input.print(String.format("Item with id %s not found.", id));
        }
        return false;
    }
}

/**
 * Class, поиск заявки по id.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
class ActionFindByName extends BaseAction {

    /**
     * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
     * @param name - наименование пункта меню
     * @param key - индекс пункта меню
     */
    ActionFindByName(String name, int key) {
        super(name, key);
    }

    /**
     * Метод, для поиска заявки по id и вывода данных по ней пользователю.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public boolean execute(Tracker tracker, Input input) {
        String name = input.ask("Enter name: ");
        ArrayList<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                input.print(String.format("Found item - %s %s", item.getId(), item.getName()));
            }
        } else {
            input.print(String.format("Item with id %s not found.", name));
        }
        return false;
    }
}

/**
 * Class - заглушка, для выхода.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
class ActionExit extends BaseAction {

    /**
     * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
     * @param name - наименование пункта меню
     * @param key - индекс пункта меню
     */
    ActionExit(String name, int key) {
        super(name, key);
    }

    /**
     * Метод, заглушка.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public boolean execute(Tracker tracker, Input input) {
        return true;
    }
}