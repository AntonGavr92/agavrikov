package ru.job4j;

/**
 * Class описывающий меню трекера.
 * @author agavrikov
 * @since 09.07.2017
 * @version 1
 */
public class MenuTracker {

    /**
     * Массив с пунктами меню.
     */
    private String[] menuItems;

    /**
     * Массив с набором действиий, соответствующих массиву menuItems.
     */
    private Action[] actions;

    /**
     * Конструктор.
     * @param menuItems - массив с пунктами
     * @param actions - массив с действие для пунктов из actions
     */
    public MenuTracker(String[] menuItems, Action[] actions) {
        this.menuItems = menuItems;
        this.actions = actions;
    }

    /**
     * Метод, возвращающий элемент по индексу (введенного пользователем).
     * @param index - индекс в массиве actions
     * @return - Объект Action
     */
    public Action select(int index) {
        return this.actions[index];
    }

    /**
     * Геттер пунктов меню.
     * @return массив с пунктами меню
     */
    public String[] getMenuItems() {
        return this.menuItems;
    }
}
