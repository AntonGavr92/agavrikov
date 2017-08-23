package ru.job4j.isp;

/**
 * Class SimpleItem.
 * @author agavrikov
 * @since 23.08.2017
 * @version 1
 */
public class SimpleItem extends Item {

    /**
     * counter for unique id.
     */
    private static int counterId = 0;

    /**
     * constructor.
     */
    public SimpleItem() {
        this.setId(counterId++);
    }

    /**
     * constructor.
     * @param name name of item
     */
    public SimpleItem(String name) {
        this.setId(counterId++);
        this.setName(name);
    }

    /**
     * method for execute command.
     */
    @Override
    public void execute() {
        //todo some things
    }
}
