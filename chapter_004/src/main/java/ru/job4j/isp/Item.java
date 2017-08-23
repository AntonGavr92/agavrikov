package ru.job4j.isp;

/**
 * Abstract class Item of menu.
 * @author agavrikov
 * @since 23.08.2017
 * @version 1
 */
public abstract class Item implements AddItemMenu, Action {

    /**
     * Submenu of Item.
     */
    private Menu subMenu;

    /**
     * Item has a sub menu;
     */
    private boolean hasSubMenu;

    /**
     * Name of Item.
     */
    private String name;

    /**
     * id of Item.
     */
    private int id;


    /**
     * Getter.
     * @return subMenu
     */
    public Menu getSubMenu() {
        return subMenu;
    }

    /**
     * Getter.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter.
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter.
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter.
     * @return if item has submenu - true, else - false.
     */
    public boolean isHasSubMenu() {
        return this.hasSubMenu;
    }

    /**
     * Method for adding submenu item.
     * @param item item menu.
     */
    @Override
    public void addItem(Item item) {
        if (!this.hasSubMenu) {
            this.subMenu = new Menu();
            this.hasSubMenu = true;
        }
        this.subMenu.addItem(item);
    }
}
