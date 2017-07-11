package ru.job4j;

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
    private UserAction[] actions;

    /**
     * Конструктор по умолчанию.
     */
    public MenuTracker() {
        this.actions = new UserAction[]{
                new UserActionAdd("Add task", 0),
                new UserActionShowAll("Show all tasks", 1),
                new UserActionEditItem("Edit task", 2),
                new UserActionDelete("Delete task", 3),
                new UserActionFindItemById("Find task by id", 4),
                new UserActionFindItemsByName("Find task by name", 5),
                new UserActionExit("Exit", 6)};
    }

    /**
     * Геттер пунктов меню.
     * @return объект UserAction
     */
    public UserAction[] getActions() {
        return this.actions;
    }

    /**
     * Метод, возвращающий элемент по индексу (введенного пользователем).
     * @param index - индекс в массиве actions
     * @return - Объект Action
     */
    public UserAction select(int index) {
        return this.actions[index];
    }

    /**
     * Класс описывающий событие добавление задачи в трекер.
     * @author agavrikov
     * @since 09.07.2017
     * @version 1
     */
    public class UserActionAdd extends BaseAction {

        /**
         * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
         * @param name - наименование пункта меню
         * @param key - индекс пункта меню
         */
        public UserActionAdd(String name, int key) {
            super(name, key);
        }

        /**
         * Метод, добавляющий новую задачу в трекер.
         * @param tracker - трекер
         * @param input - ввод данных
         */
        @Override
        public void execute(Tracker tracker, Input input) {
            Date date = new Date();
            String name = input.ask("Enter the task's name: ");
            String description = input.ask("Enter the task's description: ");

            Item[] items = tracker.findAll();
            Integer id = 0;
            if (items.length > 0) {
                id = Integer.parseInt(items[items.length - 1].getId());
                id++;
            }
            Item resultItem = tracker.add(new Item(id.toString(), name, description, date.getTime()));
            input.print("Task " + resultItem.getName() + " with id " + resultItem.getId() + " was created.");
        }
    }

    /**
     * Class, описывающий удаление заявки.
     * @author agavrikov
     * @since 09.07.2017
     * @version 1
     */
    public static class UserActionDelete extends BaseAction {

        /**
         * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
         * @param name - наименование пункта меню
         * @param key - индекс пункта меню
         */
        public UserActionDelete(String name, int key) {
            super(name, key);
        }

        /**
         * Метод, добавляющий новую задачу в трекер.
         * @param tracker - трекер
         * @param input - ввод данных
         */
        @Override
        public void execute(Tracker tracker, Input input) {
            input.print("1. Search task by id.");
            input.print("2. Search task by name.");
            input.print("3. Show all tasks.");
            //input.print("4. Back to main menu.");
            int menuItem = input.ask("Select: ", new int[]{1, 2, 3});
            if (menuItem == 1) {
                String id = input.ask("Enter id: ");
                Item item = tracker.findById(id);
                if (item != null) {
                    tracker.delete(item);
                } else {
                    input.print("Task with id " + id + " not found;");
                }
            } else if (menuItem == 2) {
                String name = input.ask("Enter name: ");
                Item[] items = tracker.findByName(name);
                if (items.length > 0) {
                    for (int i = 0; i < items.length; i++) {
                        input.print(i + ". Task " + items[i].getId() + " " + items[i].getName());
                    }
                } else {
                    input.print("Tasks with name " + name + " not found;");
                }
                tracker.delete(items[Integer.parseInt(input.ask("Select: "))]);
            } else if (menuItem == 3) {
                Item[] items = tracker.findAll();
                int[] itemsRange = new int[items.length];
                for (int i = 0; i < items.length; i++) {
                    input.print(i + ". Task " + items[i].getId() + " " + items[i].getName());
                    itemsRange[i] = i;
                }
                if (items.length > 0) {
                    tracker.delete(items[input.ask("Select: ", itemsRange)]);
                }
            }
        }
    }
}

/**
 * Class, вывод всех заявок трекера.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
class UserActionShowAll extends BaseAction {

    /**
     * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
     * @param name - наименование пункта меню
     * @param key - индекс пункта меню
     */
    UserActionShowAll(String name, int key) {
        super(name, key);
    }

    /**
     * Метод, выводящий все заявки трекера.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public void execute(Tracker tracker, Input input) {
        for (Item item : tracker.findAll()) {
            input.print("Task " +  item.getId() + " name " + item.getName());
        }
    }
}

/**
 * Class, описывающий изменение заявки.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
class UserActionEditItem extends BaseAction {

    /**
     * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
     * @param name - наименование пункта меню
     * @param key - индекс пункта меню
     */
    UserActionEditItem(String name, int key) {
        super(name, key);
    }

    /**
     * метод изменяющий выбранную заявку.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public void execute(Tracker tracker, Input input) {
        input.print("1. Search task by id.");
        input.print("2. Search task by name.");
        input.print("3. Show all tasks.");
        int menuItem = input.ask("Select: ", new int[]{1, 2, 3});
        if (menuItem == 1) {
            String id = input.ask("Enter id: ");
            Item item = tracker.findById(id);
            if (item != null) {
                constructEditMenu(tracker, input, item);
            } else {
                input.print("Task with id " + id + " not found;");
            }
        } else if (menuItem == 2) {
            String name = input.ask("Enter name: ");
            Item[] items = tracker.findByName(name);
            if (items.length > 0) {
                for (int i = 0; i < items.length; i++) {
                    input.print(i + ". Task " + items[i].getId() + " " + items[i].getName());
                }
                constructEditMenu(tracker, input, items[Integer.parseInt(input.ask("Select: "))]);
            } else {
                input.print("Tasks with name " + name + " not found;");
            }
        } else if (menuItem == 3) {
            Item[] items = tracker.findAll();
            int[] itemsRange = new int[items.length];
            for (int i = 0; i < items.length; i++) {
                input.print(i + ". Task " + items[i].getId() + " " + items[i].getName());
                itemsRange[i] = i;
            }
            if (items.length > 0) {
                constructEditMenu(tracker, input, items[input.ask("Select: ", itemsRange)]);
            }
        }
    }

    /**
     * Вспомогательный метод, для построения меню рекдактирования заявки.
     * @param tracker трекер
     * @param input объект для работы с вводом на консоль
     * @param item - заявка
     */
    public void constructEditMenu(Tracker tracker, Input input, Item item) {
        input.print("1. Edit name");
        input.print("2. Edit description");
        input.print("3. Add comment");
        int menuEdit = input.ask("Select: ", new int[]{1, 2, 3});
        if (menuEdit == 1) {
            tracker.update(new Item(item.getId(), input.ask("Enter new name: "),  item.getDesc(), item.getCreated(), item.getComments()));
        } else if (menuEdit == 2) {
            tracker.update(new Item(item.getId(), item.getName(),  input.ask("Enter new description: "), item.getCreated(), item.getComments()));
        } else if (menuEdit == 3) {
            String[] comments = new String[item.getComments().length];
            comments[item.getComments().length - 1] = input.ask("Enter comment: ");
            tracker.update(new Item(item.getId(), item.getName(),  item.getDesc(), item.getCreated(), comments));
        }
    }
}


/**
 * Class, поиск заявки по id.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
class UserActionFindItemById extends BaseAction {

    /**
     * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
     * @param name - наименование пункта меню
     * @param key - индекс пункта меню
     */
    UserActionFindItemById(String name, int key) {
        super(name, key);
    }

    /**
     * Метод, для поиска заявки по id и вывода данных по ней пользователю.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public void execute(Tracker tracker, Input input) {
        String id = input.ask("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            input.print("Found item - " + item.getId() + " " + item.getName());
        } else {
            input.print("Item with id " + id + " not found.");
        }
    }
}

/**
 * Class, поиск заявки по id.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
class UserActionFindItemsByName extends BaseAction {

    /**
     * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
     * @param name - наименование пункта меню
     * @param key - индекс пункта меню
     */
    UserActionFindItemsByName(String name, int key) {
        super(name, key);
    }

    /**
     * Метод, для поиска заявки по id и вывода данных по ней пользователю.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public void execute(Tracker tracker, Input input) {
        String name = input.ask("Enter name: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                input.print("Found item - " + item.getId() + " " + item.getName());
            }
        } else {
            input.print("Item with name - " + name + " not found");
        }
    }
}

/**
 * Class - заглушка, для выхода.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
class UserActionExit extends BaseAction {

    /**
     * Конструктор для инициализации родительского конструктора и полей с ключем и наименование пункта меню.
     * @param name - наименование пункта меню
     * @param key - индекс пункта меню
     */
    UserActionExit(String name, int key) {
        super(name, key);
    }

    /**
     * Метод, заглушка.
     * @param tracker - трекер
     * @param input - ввод данных
     */
    @Override
    public void execute(Tracker tracker, Input input) {

    }
}